package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public final class TradesmenSubscriptionPendingPayment implements ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final List<TradesmanEventEntity> tradesmen;

    private TradesmenSubscriptionPendingPayment(EventId eventId, ZonedDateTime occurredDate, List<TradesmanEventEntity> tradesmen) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.tradesmen = Objects.requireNonNull(tradesmen);
    }

    public static TradesmenSubscriptionPendingPayment withTradesmen(List<TradesmanEventEntity> tradesmen) {
        return new TradesmenSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), tradesmen);
    }

    @Override
    public EventId getEventId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public List<TradesmanEventEntity> getTradesmen() {
        return List.copyOf(tradesmen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradesmenSubscriptionPendingPayment that = (TradesmenSubscriptionPendingPayment) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        return tradesmen.equals(that.tradesmen);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + tradesmen.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TradesmenSubscriptionPendingPayment{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", tradesmen=" + tradesmen +
                '}';
    }
}
