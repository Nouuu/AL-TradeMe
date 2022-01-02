package org.larrieulacoste.noe.al.trademe.features.invoices.web;

import java.time.ZonedDateTime;

public class InvoiceResponse {
    public final String invoiceId;
    public final String memberType;
    public final String memberId;
    public final ZonedDateTime occurredDate;
    public final double amount;


    public InvoiceResponse(String invoiceId, String memberType, String memberId, ZonedDateTime occurredDate, double amount) {
        this.invoiceId = invoiceId;
        this.memberType = memberType;
        this.memberId = memberId;
        this.occurredDate = occurredDate;
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
