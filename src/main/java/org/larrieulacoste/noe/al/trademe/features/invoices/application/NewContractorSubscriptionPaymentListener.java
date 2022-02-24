package org.larrieulacoste.noe.al.trademe.features.invoices.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorNewSubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.features.invoices.application.command.CreateInvoice;
import org.larrieulacoste.noe.al.trademe.kernel.command.CommandBus;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventSubscriber;

public final class NewContractorSubscriptionPaymentListener implements EventSubscriber<ContractorNewSubscriptionPayment> {

    private final CommandBus commandBus;

    public NewContractorSubscriptionPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void accept(ContractorNewSubscriptionPayment event) {
        commandBus.send(new CreateInvoice(MemberType.CONTRACTOR, event.contractorId(), event.paymentMethod().paymentMethodType(), event.amount()));
    }
}
