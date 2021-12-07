package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Failed to add example.")
public class InvalidAddException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidAddException() {
        super("Failed to add example.");
    }
}

