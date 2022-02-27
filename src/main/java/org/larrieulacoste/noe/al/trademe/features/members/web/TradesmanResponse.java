package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

import org.larrieulacoste.noe.al.trademe.domain.model.PeriodResponse;
import org.larrieulacoste.noe.al.trademe.domain.model.SkillResponse;

record TradesmanResponse(
    String userId,
    String firstname,
    String lastname,
    String email,
    String profession,
    Double dailyRate,
    Double longitude,
    Double latitude,
    String locationName,
    List<SkillResponse> skillResponse,
    List<PeriodResponse> unavailability) {

}
