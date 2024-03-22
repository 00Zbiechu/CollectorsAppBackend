package pl.app.collectorsappbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.app.collectorsappbackend.exception.CustomErrorException;
import pl.app.collectorsappbackend.exception.errors.ErrorCodes;
import pl.app.collectorsappbackend.model.entity.CollectionEntity;
import pl.app.collectorsappbackend.model.entity.dto.CardCollection;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;
import pl.app.collectorsappbackend.repository.CardRepository;
import pl.app.collectorsappbackend.repository.CollectionRepository;
import pl.app.collectorsappbackend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final UserRepository userRepository;

    private final CardRepository cardRepository;

    @Override
    public CardCollection getUserCollection(Long userId) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomErrorException("user", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST));

        List<Long> cardCollectionIds = userEntity.getCollectionEntities()
                .stream()
                .map(card -> card.getCollectionKey().getCard().getId())
                .collect(Collectors.toList());
        return CardCollection.builder().userId(userId).cardsIds(cardCollectionIds).build();
    }

    //TODO: If key exist get entity and increment amount value else save new entity
    @Override
    public CardCollection saveInCollection(CardCollection cardCollection) {
        cardCollection.cardsIds().forEach(cardId -> collectionRepository.save(
                CollectionEntity.builder()
                        .collectionKey(
                                CollectionKey.builder()
                                        .card(cardRepository.findById(cardId).orElseThrow(() -> new CustomErrorException("card", "Not found", HttpStatus.BAD_REQUEST)))
                                        .user(userRepository.findById(cardCollection.userId()).orElseThrow(() -> new CustomErrorException("user", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.BAD_REQUEST)))
                                        .build()
                        )
                        .amount(1L)
                        .build()
        ));
        return getUserCollection(cardCollection.userId());
    }
}
