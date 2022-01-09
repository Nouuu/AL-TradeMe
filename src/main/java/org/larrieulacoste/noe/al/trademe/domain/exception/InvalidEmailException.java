package org.larrieulacoste.noe.al.trademe.domain.exception;

public final class InvalidEmailException extends IllegalArgumentException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
