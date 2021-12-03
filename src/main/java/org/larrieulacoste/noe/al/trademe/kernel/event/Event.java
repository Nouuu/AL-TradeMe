package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.larrieulacoste.noe.al.trademe.application.EventId;

import java.time.ZonedDateTime;

public interface Event<T> {

    EventId getId();

    ZonedDateTime getOccurredDate();

    T getItem();
}
