package org.larrieulacoste.noe.al.trademe.application.exception;

public final class InvalidEmailException extends IllegalArgumentException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
