package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectRequiredSkillAdded(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        String skillName,
        int requiredLevel
) implements ApplicationEvent {
    public ProjectRequiredSkillAdded {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(skillName);
    }

    public static ProjectRequiredSkillAdded of(
            EntityId projectId,
            String skillName,
            int requiredLevel
    ) {
        return new ProjectRequiredSkillAdded(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                skillName,
                requiredLevel
        );
    }
}
