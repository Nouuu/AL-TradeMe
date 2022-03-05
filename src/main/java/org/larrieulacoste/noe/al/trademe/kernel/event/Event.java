package org.larrieulacoste.noe.al.trademe.kernel.event;

import java.time.ZonedDateTime;

public interface Event {

    EventId eventId();

    ZonedDateTime occurredDate();

}
