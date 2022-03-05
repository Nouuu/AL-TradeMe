package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RetrieveTradesmanInvoicesService implements QueryHandler<RetrieveTradesmanInvoices, List<Invoice>> {
    private final Invoices invoices;

    public RetrieveTradesmanInvoicesService(Invoices invoices) {
        this.invoices = Objects.requireNonNull(invoices);
    }

    @Override
    public List<Invoice> handle(RetrieveTradesmanInvoices query) {
        return invoices.getTradesmanInvoices(query.tradesmanId());
    }
}
