package org.larrieulacoste.noe.al.trademe.features.members.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PeriodRequest;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

import java.util.List;

public record UpdateTradesmanAbilities(
    String tradesmanId,
    String profession,
    List<SkillRequest> skills,
    Double activityRadius,
    Double dailyRate,
    List<PeriodRequest> unavailabilityPeriods) implements Command {
}
