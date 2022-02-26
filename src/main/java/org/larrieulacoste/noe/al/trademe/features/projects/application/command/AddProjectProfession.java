package org.larrieulacoste.noe.al.trademe.features.projects.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record AddProjectProfession(
        String projectId,
        String profession
) implements Command {
}
