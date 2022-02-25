package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.time.ZonedDateTime;
import java.util.List;

public record CreateProject(
        String taskName,
        List<SkillRequest> skills,
        List<String> professions,
        String contractorId,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        double dailyRate,
        String locationName,
        double longitude, // Y
        double latitude // X
) implements Command {
}
