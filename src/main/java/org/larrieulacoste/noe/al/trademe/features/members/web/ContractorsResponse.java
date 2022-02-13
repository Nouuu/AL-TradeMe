package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

record ContractorsResponse(
        List<ContractorResponse> contractors,
        int count) {
}
