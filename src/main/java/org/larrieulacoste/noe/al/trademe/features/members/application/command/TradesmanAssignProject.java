package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.time.ZonedDateTime;

public record TradesmanAssignProject(
        String projectId,
        String tradesmanId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements Command {
}
