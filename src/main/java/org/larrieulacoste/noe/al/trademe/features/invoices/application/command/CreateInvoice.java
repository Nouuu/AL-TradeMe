package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.util.Objects;

public final class CreateInvoice implements Command {
    public final MemberType memberType;
    public final EntityId memberId;
    public final PaymentMethodType paymentMethodType;
    public final Amount amount;

    public CreateInvoice(MemberType memberType, EntityId memberId, PaymentMethodType paymentMethodType, Amount amount) {
        this.memberType = Objects.requireNonNull(memberType);
        this.memberId = Objects.requireNonNull(memberId);
        this.paymentMethodType = Objects.requireNonNull(paymentMethodType);
        this.amount = Objects.requireNonNull(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateInvoice that = (CreateInvoice) o;

        if (memberType != that.memberType) return false;
        if (!memberId.equals(that.memberId)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = memberType.hashCode();
        result = 31 * result + memberId.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreateInvoice{" +
                "memberType=" + memberType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                '}';
    }
}
