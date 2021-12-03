package org.larrieulacoste.noe.al.domain.exception;

public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }
}
