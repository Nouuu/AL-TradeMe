package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.time.ZonedDateTime;

public record UpdateProject(
        String projectId,
        String taskName,
        ZonedDateTime startDate, // TODO problem, in modifiable
        ZonedDateTime endDate, // TODO problem, in modifiable
        Double dailyRate,
        String locationName,
        Double longitude, // Y
        Double latitude // X
) implements Command {
}
