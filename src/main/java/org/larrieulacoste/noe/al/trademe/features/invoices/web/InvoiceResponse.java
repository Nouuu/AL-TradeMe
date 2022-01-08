package org.larrieulacoste.noe.al.trademe.features.invoices.web;

import java.time.ZonedDateTime;

final class InvoiceResponse {
    final String invoiceId;
    final String memberType;
    final String memberId;
    final ZonedDateTime occurredDate;
    final String paymentMethodType;
    final double amount;


    InvoiceResponse(String invoiceId, String memberType, String memberId, ZonedDateTime occurredDate, String paymentMethodType, double amount) {
        this.invoiceId = invoiceId;
        this.memberType = memberType;
        this.memberId = memberId;
        this.occurredDate = occurredDate;
        this.paymentMethodType = paymentMethodType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceResponse{" +
                "invoiceId='" + invoiceId + '\'' +
                ", memberType='" + memberType + '\'' +
                ", memberId='" + memberId + '\'' +
                ", occurredDate=" + occurredDate +
                ", amount=" + amount +
                '}';
    }
}
