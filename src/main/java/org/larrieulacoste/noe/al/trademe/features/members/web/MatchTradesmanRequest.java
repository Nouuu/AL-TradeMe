package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.Date;
import java.util.List;

public record MatchTradesmanRequest(String projectId,
                                    List<String> requiredSkills,
                                    String profession,
                                    Date startDate,
                                    Date endDate,
                                    double dailyRate,
                                    double latitude,
                                    double longitude) {
}
