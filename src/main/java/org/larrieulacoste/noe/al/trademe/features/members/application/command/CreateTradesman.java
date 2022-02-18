package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record CreateTradesman(
                String firstname,
                String lastname,
                String email,
                String password,
                String paymentMethodType,
                String paymentMethodRessource,
                String profession,
                double longitude,
                double latitiude,
                double activityRaidus,
                double dailyRate,
                String locationName

) implements Command {
}
