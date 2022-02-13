package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record CreateContractor(String firstname,
                               String lastname,
                               String email,
                               String password,
                               String paymentMethodType,
                               String paymentMethodRessource
) implements Command {
}
