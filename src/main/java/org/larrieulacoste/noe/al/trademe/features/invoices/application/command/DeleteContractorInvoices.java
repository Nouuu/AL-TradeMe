package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class DeleteContractorInvoices implements Command {
    public final EntityId contractorId;

    public DeleteContractorInvoices(EntityId contractorId) {
        this.contractorId = contractorId;
    }

    @Override
    public String toString() {
        return "DeleteContractorInvoices{" +
                "contractorId=" + contractorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteContractorInvoices that = (DeleteContractorInvoices) o;

        return contractorId.equals(that.contractorId);
    }

    @Override
    public int hashCode() {
        return contractorId.hashCode();
    }
}
