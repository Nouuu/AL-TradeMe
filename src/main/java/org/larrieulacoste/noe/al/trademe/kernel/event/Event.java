package org.larrieulacoste.noe.al.trademe.features.members.infrastructure.kernel.event;

import java.time.ZonedDateTime;

public interface Event {

    EventId getEventId();

    ZonedDateTime getOccurredDate();

}
