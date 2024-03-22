package pl.app.collectorsappbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.app.collectorsappbackend.exception.CustomErrorException;
import pl.app.collectorsappbackend.exception.errors.ErrorCodes;
import pl.app.collectorsappbackend.model.dto.AddCardsToCollection;
import pl.app.collectorsappbackend.model.dto.CardAmount;
import pl.app.collectorsappbackend.model.dto.CardsCollection;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.model.entity.CollectionEntity;
import pl.app.collectorsappbackend.model.entity.UserEntity;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;
import pl.app.collectorsappbackend.repository.CardRepository;
import pl.app.collectorsappbackend.repository.CollectionRepository;
import pl.app.collectorsappbackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final UserRepository userRepository;

    private final CardRepository cardRepository;

    @Override
    public CardsCollection getUserCollection(Long userId) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomErrorException("user", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));

        List<CardAmount> cardAmount = userEntity.getCollectionEntities()
                .stream()
                .map(card -> CardAmount.builder()
                        .cardId(card.getCollectionKey().getCard().getId())
                        .cardAmount(card.getAmount())
                        .build())
                .toList();
        return CardsCollection.builder().userId(userId).cardAmountList(cardAmount).build();
    }

    @Override
    public CardsCollection saveInCollection(AddCardsToCollection addCardsToCollection) {
        var userEntity = userRepository.findById(addCardsToCollection.userId()).orElseThrow(() -> new CustomErrorException("user", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));
        addCardsToCollection.cardsIds().forEach(cardId ->
        {
            var cardEntity = cardRepository.findById(cardId).orElseThrow(
                    () -> new CustomErrorException("card", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));
            Optional<CollectionEntity> collectionEntity = collectionRepository.findByCollectionKey(cardEntity.getId(), userEntity.getId());

            if (collectionEntity.isPresent()) {
                incrementCardAmount(collectionEntity.get());
                return;
            }
            saveNewCardInCollection(cardEntity, userEntity);
        });
        return getUserCollection(addCardsToCollection.userId());
    }

    @Override
    public CardsCollection trade(Long userId, Long oldCardId, Long newCardId) {
        var collection = collectionRepository.findByCollectionKey(oldCardId, userId).orElseThrow(
                () -> new CustomErrorException("collection", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));

        if (collection.getAmount() > 1) {
            collection.setAmount(collection.getAmount() - 1);
            collectionRepository.save(collection);
            saveInCollection(AddCardsToCollection.builder().userId(userId).cardsIds(List.of(newCardId)).build());
        } else if (collection.getAmount() <= 1) {
            collectionRepository.delete(collection);
            saveInCollection(AddCardsToCollection.builder().userId(userId).cardsIds(List.of(newCardId)).build());
        }

        return getUserCollection(userId);
    }

    private void saveNewCardInCollection(CardEntity cardEntity, UserEntity userEntity) {
        collectionRepository.save(
                CollectionEntity.builder()
                        .collectionKey(
                                CollectionKey.builder()
                                        .card(cardEntity)
                                        .user(userEntity)
                                        .build()
                        )
                        .amount(1L)
                        .build()
        );
    }

    private void incrementCardAmount(CollectionEntity collectionEntity) {
        Long amount = collectionEntity.getAmount();
        collectionEntity.setAmount(++amount);
        collectionRepository.save(collectionEntity);
    }
}
