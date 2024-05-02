package org.myungkeun.coworking240502.exception.badRequest;

import lombok.Getter;
import org.myungkeun.coworking240502.exception.CoworkingException;
import org.springframework.http.HttpStatus;

@Getter

public class BadRequestException extends CoworkingException {
    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
