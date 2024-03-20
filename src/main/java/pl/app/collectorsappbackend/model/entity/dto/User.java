package pl.app.collectorsappbackend.model.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record User(Long id, @NotBlank String username) {
}
