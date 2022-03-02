package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PeriodRequest;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillRequest;

import java.util.List;

record TradesmanAbilitiesRequest(String tradesmanId,
        String profession,
        List<SkillRequest> skills,
        Double activityRadius,
        Double dailyRate,
        List<PeriodRequest> unavailabilityPeriods) {

}
