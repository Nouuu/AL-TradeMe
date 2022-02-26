package org.larrieulacoste.noe.al.trademe.application.event;

import java.time.ZonedDateTime;
import java.util.Objects;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.Period;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

public record TradesmanAssigned(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        EntityId tradesmanId,
        Period period
) implements ApplicationEvent {

    public TradesmanAssigned {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(tradesmanId);
        Objects.requireNonNull(period);
    }

    public static TradesmanAssigned of(
            EntityId projectId,
            EntityId tradesmanId,
            Period period
    ) {
        return new TradesmanAssigned(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                tradesmanId,
                period
        );
    }
}
