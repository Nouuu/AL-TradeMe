package org.larrieulacoste.noe.al.trademe.features.members.web;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PeriodResponse;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.SkillResponse;

import java.util.List;

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
