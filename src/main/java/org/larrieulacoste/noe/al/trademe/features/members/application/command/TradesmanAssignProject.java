package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import java.time.ZonedDateTime;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record TradesmanAssignProject(
        String projectId,
        String tradesmanId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements Command {
}
