package org.larrieulacoste.noe.al.trademe.features.payment.application;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.util.Objects;

public final class ContractorPayment implements Command {
    public final EntityId contractorId;
    public final String paymentMethod;

    public ContractorPayment(EntityId contractorId, String paymentMethod) {
        this.contractorId = contractorId;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorPayment that = (ContractorPayment) o;
        return Objects.equals(contractorId, that.contractorId) && Objects.equals(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorId, paymentMethod);
    }

    @Override
    public String toString() {
        return "ContractorPayment{" +
                "contractorId=" + contractorId +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
