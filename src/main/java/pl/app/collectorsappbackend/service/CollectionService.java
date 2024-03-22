package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.dto.AddCardsToCollection;
import pl.app.collectorsappbackend.model.dto.CardsCollection;

public interface CollectionService {

    CardsCollection getUserCollection(Long userId);

    CardsCollection saveInCollection(AddCardsToCollection addCardsToCollection);

    CardsCollection trade(Long userId, Long oldCardId, Long newCardId);
}
