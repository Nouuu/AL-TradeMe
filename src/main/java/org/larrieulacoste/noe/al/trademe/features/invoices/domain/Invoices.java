package org.larrieulacoste.noe.al.trademe.features.invoices.domain;

import org.larrieulacoste.noe.al.trademe.domain.Repository;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;

import java.util.List;

public interface Invoices extends Repository<Invoice> {

    List<Invoice> getTradesmenInvoices();

    List<Invoice> getContractorsInvoices();

    List<Invoice> getTradesmanInvoices(EntityId tradesmanId);

    List<Invoice> getContractorInvoices(EntityId contractorId);
}
