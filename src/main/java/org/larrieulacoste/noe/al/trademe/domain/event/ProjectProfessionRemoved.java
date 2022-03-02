package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectProfessionRemoved(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        String professionRemoved
) implements ApplicationEvent {
    public ProjectProfessionRemoved {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(professionRemoved);
    }

    public static ProjectProfessionRemoved of(
            EntityId projectId,
            String professionAdded
    ) {
        return new ProjectProfessionRemoved(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                professionAdded
        );
    }
}
