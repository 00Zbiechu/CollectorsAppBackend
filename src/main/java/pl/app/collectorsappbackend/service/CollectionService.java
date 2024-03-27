package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.dto.AddCardsToCollection;
import pl.app.collectorsappbackend.model.dto.CardsCollection;

public interface CollectionService {

    CardsCollection getUserCollection();

    CardsCollection saveInCollection(AddCardsToCollection addCardsToCollection);

    CardsCollection trade(Long oldCardId, Long newCardId);
}
