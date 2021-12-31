package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.EntityId;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberType;

import java.time.ZonedDateTime;
import java.util.Objects;

public class InvoiceEventEntity {
    private final EntityId invoiceId;
    private final MemberType memberType;
    private final EntityId memberId;
    private final ZonedDateTime occurredDate;
    private final double amount;

    private InvoiceEventEntity(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, double amount) {
        this.invoiceId = Objects.requireNonNull(invoiceId);
        this.memberType = Objects.requireNonNull(memberType);
        this.memberId = Objects.requireNonNull(memberId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.amount = amount;
    }

    public static InvoiceEventEntity of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, double amount) {
        return new InvoiceEventEntity(invoiceId, memberType, memberId, occurredDate, amount);
    }

    public EntityId getInvoiceId() {
        return invoiceId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public EntityId getMemberId() {
        return memberId;
    }

    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public double getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceEventEntity that = (InvoiceEventEntity) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (!Objects.equals(invoiceId, that.invoiceId)) return false;
        if (memberType != that.memberType) return false;
        if (!memberId.equals(that.memberId)) return false;
        return occurredDate.equals(that.occurredDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = invoiceId.hashCode();
        result = 31 * result + memberType.hashCode();
        result = 31 * result + memberId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
