package pl.app.collectorsappbackend.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CardsCollection(Long userId, List<CardAmount> cardAmountList) {
}
