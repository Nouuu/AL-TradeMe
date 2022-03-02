package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

public record AddProjectRequiredSkill(
        String projectId,
        SkillRequest requiredSkill
) implements Command {
}
