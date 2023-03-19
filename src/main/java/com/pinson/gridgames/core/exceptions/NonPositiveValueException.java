package com.pinson.gridgames.core.exceptions;

public class NonPositiveValueException extends Exception {
    public NonPositiveValueException() {
        super("Value must be positive.");
    }

    public NonPositiveValueException(String message) {
        super(message);
    }
}
