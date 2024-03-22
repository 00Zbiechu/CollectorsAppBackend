package pl.app.collectorsappbackend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomErrorException extends RuntimeException {

    private String field;

    private String errorCode;

    private HttpStatus httpStatus;

    public CustomErrorException(String field, String errorCode, HttpStatus httpStatus) {
        this.field = field;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
