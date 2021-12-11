package org.larrieulacoste.noe.al.trademe.application.exception;

public final class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
