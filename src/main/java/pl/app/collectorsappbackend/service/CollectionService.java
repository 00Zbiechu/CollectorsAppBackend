package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.entity.dto.CardCollection;

public interface CollectionService {

    CardCollection getUserCollection(Long userId);

    CardCollection saveInCollection(CardCollection cardCollection);
}
