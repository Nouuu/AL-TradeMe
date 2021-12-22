package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

final class ContractorsResponse {
    public final List<ContractorResponse> contractors;
    public final int count;

    public ContractorsResponse(List<ContractorResponse> contractors, int count) {
        this.contractors = contractors;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ContractorsResponse{" +
                "contractors=" + contractors +
                ", count=" + count +
                '}';
    }
}
