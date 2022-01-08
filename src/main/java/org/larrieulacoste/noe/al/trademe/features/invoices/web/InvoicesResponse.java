package org.larrieulacoste.noe.al.trademe.features.invoices.web;

import java.util.List;

final class InvoicesResponse {
    final List<InvoiceResponse> invoices;
    final int count;

    InvoicesResponse(List<InvoiceResponse> invoices, int count) {
        this.invoices = invoices;
        this.count = count;
    }

    @Override
    public String toString() {
        return "InvoicesResponse{" +
                "invoices=" + invoices +
                ", count=" + count +
                '}';
    }
}
