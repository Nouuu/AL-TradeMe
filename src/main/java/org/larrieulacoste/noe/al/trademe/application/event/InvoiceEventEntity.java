package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;
import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethodType;

import java.time.ZonedDateTime;

public class InvoiceEventEntity {
    public final EntityId invoiceId;
    public final MemberType memberType;
    public final EntityId memberId;
    public final ZonedDateTime occurredDate;
    public final PaymentMethodType paymentMethodType;
    public final Amount amount;

    private InvoiceEventEntity(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethodType, Amount amount) {
        this.invoiceId = invoiceId;
        this.memberType = memberType;
        this.memberId = memberId;
        this.occurredDate = occurredDate;
        this.paymentMethodType = paymentMethodType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceEventEntity{" +
                "invoiceId=" + invoiceId +
                ", memberType=" + memberType +
                ", memberId=" + memberId +
                ", occurredDate=" + occurredDate +
                ", paymentMethodType=" + paymentMethodType +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceEventEntity that = (InvoiceEventEntity) o;

        if (!invoiceId.equals(that.invoiceId)) return false;
        if (memberType != that.memberType) return false;
        if (!memberId.equals(that.memberId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        if (paymentMethodType != that.paymentMethodType) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = invoiceId.hashCode();
        result = 31 * result + memberType.hashCode();
        result = 31 * result + memberId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + paymentMethodType.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    public static InvoiceEventEntity of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethodType, Amount amount) {
        return new InvoiceEventEntity(invoiceId, memberType, memberId, occurredDate, paymentMethodType, amount);
    }

}
