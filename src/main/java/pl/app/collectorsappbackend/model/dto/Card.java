package pl.app.collectorsappbackend.model.dto;

import jakarta.validation.constraints.NotBlank;

public record Card(Long id, @NotBlank byte[] image) {
}
