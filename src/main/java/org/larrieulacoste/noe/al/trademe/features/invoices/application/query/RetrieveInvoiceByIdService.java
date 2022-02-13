package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class RetrieveInvoiceByIdService implements QueryHandler<RetrieveInvoiceById, Invoice> {
    private final Invoices invoices;

    public RetrieveInvoiceByIdService(Invoices invoices) {
        this.invoices = Objects.requireNonNull(invoices);
    }

    @Override
    public Invoice handle(RetrieveInvoiceById query) {
        return invoices.byId(query.invoiceId());
    }
}
