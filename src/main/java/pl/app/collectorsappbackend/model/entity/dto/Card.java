package pl.app.collectorsappbackend.model.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record Card(Long id, @NotBlank byte[] image) {
}
