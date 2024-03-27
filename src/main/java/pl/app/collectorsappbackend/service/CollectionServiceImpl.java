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
import pl.app.collectorsappbackend.security.CurrentUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final CurrentUser currentUser;

    private final CardRepository cardRepository;

    @Override
    public CardsCollection getUserCollection() {
        List<CollectionEntity> collectionEntities = collectionRepository.findByCollectionKeyUserId(currentUser.getCurrentUser().getId());
        return CardsCollection.builder()
                .cardAmountList(
                        collectionEntities.stream()
                                .map(collection -> CardAmount.builder()
                                        .cardId(collection.getCollectionKey().getCard().getId())
                                        .cardAmount(collection.getAmount())
                                        .build())
                                .toList()
                )
                .build();
    }

    @Override
    public CardsCollection saveInCollection(AddCardsToCollection addCardsToCollection) {
        addCardsToCollection.cardsIds().forEach(cardId ->
        {
            var cardEntity = cardRepository.findById(cardId).orElseThrow(
                    () -> new CustomErrorException("card", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));

            collectionRepository.findByCollectionKey(cardEntity.getId(), currentUser.getCurrentUser().getId())
                    .ifPresentOrElse(this::incrementCardAmount, () -> saveNewCardInCollection(cardEntity, currentUser.getCurrentUser()));
        });
        return getUserCollection();
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

    @Override
    public CardsCollection trade(Long oldCardId, Long newCardId) {
        CollectionEntity collection = collectionRepository.findByCollectionKey(oldCardId, currentUser.getCurrentUser().getId())
                .orElseThrow(() -> new CustomErrorException("collection", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));

        var newAmount = Math.max(collection.getAmount() - 1, 0);
        collection.setAmount(newAmount);
        collectionRepository.save(collection);

        if (newAmount == 0) {
            collectionRepository.delete(collection);
        }

        saveInCollection(AddCardsToCollection.builder().cardsIds(List.of(newCardId)).build());
        return getUserCollection();
    }
}
