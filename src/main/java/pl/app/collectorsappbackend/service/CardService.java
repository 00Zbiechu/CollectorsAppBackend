package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.entity.dto.Card;

import java.util.List;

public interface CardService {

    List<Card> getAll();

    Card save(Card card);
}
