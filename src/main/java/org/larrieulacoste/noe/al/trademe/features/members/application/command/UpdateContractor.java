package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record UpdateContractor(
        String contractorId,
        String firstname,
        String lastname,
        String email,
        String password
) implements Command {
}
