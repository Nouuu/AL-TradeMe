package org.larrieulacoste.noe.al.trademe.features.invoices.web;

import java.util.List;

record InvoicesResponse(
        List<InvoiceResponse> invoices,
        int count
) {
}
