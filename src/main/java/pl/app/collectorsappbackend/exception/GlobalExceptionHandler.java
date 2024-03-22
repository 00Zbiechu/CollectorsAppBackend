package pl.app.collectorsappbackend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.app.collectorsappbackend.exception.model.Error;
import pl.app.collectorsappbackend.exception.wrapper.ErrorList;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorList> handleCustomErrorException(CustomErrorException ex) {
        Error error = Error.builder().field(ex.getField()).errorCodes(ex.getErrorCode()).build();
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorList.builder().errorList(List.of(error)).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorList> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errorList.add(Error.builder().field(fieldName).errorCodes(errorMessage).build());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorList.builder().errorList(errorList).build());
    }
}