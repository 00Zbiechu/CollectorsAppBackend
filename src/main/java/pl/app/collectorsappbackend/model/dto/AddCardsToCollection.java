package pl.app.collectorsappbackend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record AddCardsToCollection(@NotNull Long userId, @NotNull List<Long> cardsIds) {
}
