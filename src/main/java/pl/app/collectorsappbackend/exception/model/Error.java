package pl.app.collectorsappbackend.exception.model;

import lombok.Builder;

@Builder
public record Error(String field, String errorCodes) {
}
