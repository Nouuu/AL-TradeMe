package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectCreated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        String taskName,
        EntityId contractorId,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        double dailyRate,
        String locationName
) implements ApplicationEvent {

    public ProjectCreated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(taskName);
        Objects.requireNonNull(contractorId);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(locationName);
    }

    public static ProjectCreated of(
            EntityId projectId,
            String taskName,
            EntityId contractorId,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            double dailyRate,
            String locationName
    ) {
        return new ProjectCreated(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                taskName,
                contractorId,
                startDate,
                endDate,
                dailyRate,
                locationName
        );
    }
}
