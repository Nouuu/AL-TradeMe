package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.kernel.command.Command;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;

public record DeleteTradesmanInvoices(
        EntityId tradesmanId) implements Command {
}
