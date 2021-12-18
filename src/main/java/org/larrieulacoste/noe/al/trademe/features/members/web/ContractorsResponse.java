package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

final class ContractorsResponse {
    public final List<ContractorResponse> contractors;

    public ContractorsResponse(List<ContractorResponse> contractors) {
        this.contractors = contractors;
    }

    @Override
    public String toString() {
        return "ContractorsResponse{" +
                "contractors=" + contractors +
                '}';
    }
}
