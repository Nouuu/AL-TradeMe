package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record RemoveProjectRequiredSkill(
        String projectId,
        SkillRequest requiredSkill
) implements Command {
}
