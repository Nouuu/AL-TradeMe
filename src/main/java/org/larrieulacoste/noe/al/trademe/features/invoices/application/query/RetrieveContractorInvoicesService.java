package org.larrieulacoste.noe.al.trademe.features.invoices.application.query;

import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoice;
import org.larrieulacoste.noe.al.trademe.features.invoices.domain.Invoices;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RetrieveContractorInvoicesService implements QueryHandler<RetrieveContractorInvoices, List<Invoice>> {
    private final Invoices invoices;

    public RetrieveContractorInvoicesService(Invoices invoices) {
        this.invoices = Objects.requireNonNull(invoices);
    }

    @Override
    public List<Invoice> handle(RetrieveContractorInvoices query) {
        return invoices.getContractorInvoices(query.contractorId());
    }
}
