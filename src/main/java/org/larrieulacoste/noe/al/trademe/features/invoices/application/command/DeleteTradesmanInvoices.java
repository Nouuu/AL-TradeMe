package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

public final class DeleteTradesmanInvoices implements Command {
    public final EntityId tradesmanId;

    public DeleteTradesmanInvoices(EntityId tradesmanId) {
        this.tradesmanId = tradesmanId;
    }

    @Override
    public String toString() {
        return "DeleteTradesmanInvoices{" +
                "tradesmanId=" + tradesmanId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteTradesmanInvoices that = (DeleteTradesmanInvoices) o;

        return tradesmanId.equals(that.tradesmanId);
    }

    @Override
    public int hashCode() {
        return tradesmanId.hashCode();
    }
}
