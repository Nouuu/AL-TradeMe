package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

import java.time.ZonedDateTime;
import java.util.List;

public record CreateProject(
        String taskName,
        List<SkillRequest> skills,
        List<String> professions,
        String contractorId,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        Double dailyRate,
        String locationName,
        Double longitude, // Y
        Double latitude // X
) implements Command {
}
