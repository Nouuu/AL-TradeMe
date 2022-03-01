package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import java.util.List;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PeriodRequest;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public record UpdateTradesmanAbilities(
    String tradesmanId,
    String profession,
    List<SkillRequest> skills,
    Double activityRadius,
    Double dailyRate,
    List<PeriodRequest> unavailabilityPeriods) implements Command {
}
