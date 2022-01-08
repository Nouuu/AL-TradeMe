package org.larrieulacoste.noe.al.trademe.features.invoices.domain;

import org.larrieulacoste.noe.al.trademe.domain.model.*;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class Invoice {
    private final EntityId invoiceId;
    private final MemberType memberType;
    private final EntityId memberId;
    private final ZonedDateTime occurredDate;
    private final PaymentMethodType paymentMethodType;
    private final Amount amount;

    private Invoice(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethodType, Amount amount) {
        this.invoiceId = Objects.requireNonNull(invoiceId);
        this.memberType = Objects.requireNonNull(memberType);
        this.memberId = Objects.requireNonNull(memberId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.paymentMethodType = Objects.requireNonNull(paymentMethodType);
        this.amount = Objects.requireNonNull(amount);
    }

    public static Invoice of(EntityId invoiceId, MemberType memberType, EntityId memberId, ZonedDateTime occurredDate, PaymentMethodType paymentMethod, Amount amount) {
        return new Invoice(invoiceId, memberType, memberId, occurredDate, paymentMethod, amount);
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

    public Amount getAmount() {
        return amount;
    }

    public PaymentMethodType getPaymentMethodType() {
        return paymentMethodType;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", memberType=" + memberType +
                ", memberId=" + memberId +
                ", occurredDate=" + occurredDate +
                ", paymentMethod=" + paymentMethodType +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (!invoiceId.equals(invoice.invoiceId)) return false;
        if (memberType != invoice.memberType) return false;
        if (!memberId.equals(invoice.memberId)) return false;
        if (!occurredDate.equals(invoice.occurredDate)) return false;
        if (!paymentMethodType.equals(invoice.paymentMethodType)) return false;
        return amount.equals(invoice.amount);
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

}
