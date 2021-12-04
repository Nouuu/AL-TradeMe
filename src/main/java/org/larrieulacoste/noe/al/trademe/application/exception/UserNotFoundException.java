package org.larrieulacoste.noe.al.trademe.application.exception;

public final class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
