package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

final class ContractorsResponse {
    final List<ContractorResponse> contractors;
    final int count;

    ContractorsResponse(List<ContractorResponse> contractors, int count) {
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
