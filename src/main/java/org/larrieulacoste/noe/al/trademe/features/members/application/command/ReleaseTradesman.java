package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import java.time.ZonedDateTime;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record ReleaseTradesman(
        EntityId tradesmanId,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) implements Command {
}
