package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot complete request.")
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super("Failed to add example.");
    }
}

