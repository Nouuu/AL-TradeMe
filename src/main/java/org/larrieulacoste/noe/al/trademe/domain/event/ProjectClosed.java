package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectClosed(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements ApplicationEvent {

    public ProjectClosed {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
    }

    public static ProjectClosed of(
            EntityId projectId,
            ZonedDateTime startDate,
            ZonedDateTime endDate
    ) {
        return new ProjectClosed(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                startDate,
                endDate
        );
    }
}
