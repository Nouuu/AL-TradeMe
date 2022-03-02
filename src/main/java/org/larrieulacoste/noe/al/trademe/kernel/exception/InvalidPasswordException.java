package org.larrieulacoste.noe.al.trademe.kernel.exception;

public final class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
