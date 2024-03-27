package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.dto.Card;

import java.util.List;

public interface CardService {

    List<Card> getAll();
}
