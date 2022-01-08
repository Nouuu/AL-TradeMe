package org.larrieulacoste.noe.al.trademe.application.exception;

import java.time.ZonedDateTime;

final class ExceptionResponse {

    public final String source;
    public final ZonedDateTime occurredDate;
    public final String message;

    private ExceptionResponse(String source, ZonedDateTime occurredDate, String message) {
        this.source = source;
        this.occurredDate = occurredDate;
        this.message = message;
    }

    public static ExceptionResponse withSourceAndMessage(String source, String message) {
        return new ExceptionResponse(source, ZonedDateTime.now(), message);
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "source='" + source + '\'' +
                ", occurredDate=" + occurredDate +
                ", message='" + message + '\'' +
                '}';
    }
}
