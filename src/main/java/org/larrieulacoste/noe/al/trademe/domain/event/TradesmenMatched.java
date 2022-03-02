package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public record TradesmenMatched(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        List<EntityId> tradesmenIds
) implements ApplicationEvent {
    public TradesmenMatched {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(tradesmenIds);
    }

    public static TradesmenMatched of(EntityId projectId, List<EntityId> tradesmenIds) {
        return new TradesmenMatched(EventId.create(), ZonedDateTime.now(), projectId, tradesmenIds);
    }
}
