package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanTerminated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        EntityId tradesmanId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements ApplicationEvent {
    public TradesmanTerminated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(tradesmanId);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
    }

    public static TradesmanTerminated of(
            EntityId projectId,
            EntityId tradesmanId,
            ZonedDateTime startDate,
            ZonedDateTime endDate) {
        return new TradesmanTerminated(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                tradesmanId,
                startDate,
                endDate
        );
    }
}
