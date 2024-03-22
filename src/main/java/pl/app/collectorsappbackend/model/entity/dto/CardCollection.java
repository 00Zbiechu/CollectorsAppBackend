package pl.app.collectorsappbackend.model.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CardCollection(@NotNull Long userId, @NotNull List<Long> cardsIds) {
}
