package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

import java.time.ZonedDateTime;

public record ReleaseTradesman(
        EntityId tradesmanId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements Command {
}
