package org.larrieulacoste.noe.al.domain.event;

import java.time.ZonedDateTime;

public interface Event<T> {

    EventId getId();

    ZonedDateTime getOccurredDate();

    T getItem();
}
