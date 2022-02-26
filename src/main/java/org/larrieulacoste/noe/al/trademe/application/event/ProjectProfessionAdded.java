package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectProfessionAdded(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        String professionAdded
) implements ApplicationEvent {
    public ProjectProfessionAdded {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(professionAdded);
    }

    public static ProjectProfessionAdded of(
            EntityId projectId,
            String professionAdded
    ) {
        return new ProjectProfessionAdded(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                professionAdded
        );
    }
}
