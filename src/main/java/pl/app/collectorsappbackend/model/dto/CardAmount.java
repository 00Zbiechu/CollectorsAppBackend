package pl.app.collectorsappbackend.model.dto;

import lombok.Builder;

@Builder
public record CardAmount(Long cardId, Long cardAmount) {
}
