package org.larrieulacoste.noe.al.trademe.features.invoices.web;

import java.time.ZonedDateTime;

record InvoiceResponse(
        String invoiceId,
        String memberType,
        String memberId,
        ZonedDateTime occurredDate,
        String paymentMethodType,
        double amount
) {

}
