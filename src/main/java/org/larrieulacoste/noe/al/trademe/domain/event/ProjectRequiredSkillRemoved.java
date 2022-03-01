package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ProjectRequiredSkillRemoved(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId projectId,
        String skillName,
        int requiredLevel
) implements ApplicationEvent {
    public ProjectRequiredSkillRemoved {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(skillName);
    }

    public static ProjectRequiredSkillRemoved of(
            EntityId projectId,
            String skillName,
            int requiredLevel
    ) {
        return new ProjectRequiredSkillRemoved(
                EventId.create(),
                ZonedDateTime.now(),
                projectId,
                skillName,
                requiredLevel
        );
    }
}
