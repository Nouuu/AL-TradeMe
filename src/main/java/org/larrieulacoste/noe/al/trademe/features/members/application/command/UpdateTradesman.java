package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record UpdateTradesman(
        String tradesmanId,
        String firstname,
        String lastname,
        String email,
        String password,
        String locationName,
        Double longitude,
        Double latitude) implements Command {
}
