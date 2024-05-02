package org.myungkeun.coworking240502.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public class CoworkingException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;
    private final int code;

    public CoworkingException(HttpStatus httpStatus, String message, int code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}
