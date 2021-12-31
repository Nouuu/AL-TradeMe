package org.larrieulacoste.noe.al.trademe.features.invoices.application.command;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.kernel.command.Command;

import java.util.Objects;

public class CreateInvoice implements Command {
    public final MemberType memberType;
    public final EntityId memberId;
    public final double amount;

    public CreateInvoice(MemberType memberType, EntityId memberId, double amount) {
        this.memberType = Objects.requireNonNull(memberType);
        this.memberId = Objects.requireNonNull(memberId);
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateInvoice that = (CreateInvoice) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (memberType != that.memberType) return false;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = memberType != null ? memberType.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
