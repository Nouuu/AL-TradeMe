package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.Amount;
import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;

import java.time.ZonedDateTime;
import java.util.Objects;

public class InvoiceEventEntity {
    public final EntityId invoiceId;
    public final MemberType memberType;
    public final EntityId memberId;
    public final ZonedDateTime occurredDate;
    public final Amount amount;

    private InvoiceEventEntity(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, Amount amount) {
        this.invoiceId = Objects.requireNonNull(invoiceId);
        this.memberType = Objects.requireNonNull(memberType);
        this.memberId = Objects.requireNonNull(memberId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.amount = Objects.requireNonNull(amount);
    }

    public static InvoiceEventEntity of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, Amount amount) {
        return new InvoiceEventEntity(invoiceId, memberType, memberId, occurredDate, amount);
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
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = invoiceId.hashCode();
        result = 31 * result + memberType.hashCode();
        result = 31 * result + memberId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", memberType=" + memberType +
                ", memberId=" + memberId +
                ", occurredDate=" + occurredDate +
                ", amount=" + amount +
                '}';
    }
}
