package org.larrieulacoste.noe.al.trademe.application.exception;

import java.time.ZonedDateTime;

public record ExceptionResponse(
        String source,
        ZonedDateTime occurredDate,
        String message
) {

    public static ExceptionResponse withSourceAndMessage(String source, String message) {
        return new ExceptionResponse(source, ZonedDateTime.now(), message);
    }
}
