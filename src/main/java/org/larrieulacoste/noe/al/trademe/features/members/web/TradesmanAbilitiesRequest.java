package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

import org.larrieulacoste.noe.al.trademe.domain.model.PeriodRequest;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillRequest;

public record TradesmanAbilitiesRequest(String tradesmanId,
        String profession,
        List<SkillRequest> skills,
        Double activityRadius,
        Double dailyRate,
        List<PeriodRequest> unavailabilityPeriods) {

}
