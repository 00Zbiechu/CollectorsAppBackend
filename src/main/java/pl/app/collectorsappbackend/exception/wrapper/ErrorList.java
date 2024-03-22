package pl.app.collectorsappbackend.exception.wrapper;

import lombok.Builder;
import pl.app.collectorsappbackend.exception.model.Error;

import java.util.List;

@Builder
public record ErrorList(List<Error> errorList) {
}
