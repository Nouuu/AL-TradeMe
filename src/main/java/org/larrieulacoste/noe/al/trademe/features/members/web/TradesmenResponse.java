package org.larrieulacoste.noe.al.trademe.features.members.web;

import java.util.List;

final class TradesmenResponse {
    final List<TradesmanResponse> tradesmen;
    final int count;

    TradesmenResponse(List<TradesmanResponse> tradesmen, int count) {
        this.tradesmen = tradesmen;
        this.count = count;
    }

    @Override
    public String toString() {
        return "TradesmenResponse{" +
                "tradesmen=" + tradesmen +
                ", count=" + count +
                '}';
    }
}
