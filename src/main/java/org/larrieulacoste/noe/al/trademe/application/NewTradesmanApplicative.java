package org.larrieulacoste.noe.al.trademe.application;

import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class NewTradesmanApplicative implements ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final User user;
    private final Double amount;

    private NewTradesmanApplicative(EventId eventId, ZonedDateTime occurredDate, User user, Double amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.user = Objects.requireNonNull(user);
        this.amount = amount;
    }

    public static NewTradesmanApplicative withUserAndAmount(User user, Double amount) {
        return new NewTradesmanApplicative(EventId.create(), ZonedDateTime.now(), user, amount);
    }

    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public User getTradesman() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "UserApplicationEvent{" +
                "eventId=" + eventId +
                ", occurredDate=" + occurredDate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewTradesmanApplicative)) return false;

        NewTradesmanApplicative that = (NewTradesmanApplicative) o;

        if (!eventId.equals(that.eventId)) return false;
        if (!occurredDate.equals(that.occurredDate)) return false;
        if (!user.equals(that.user)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + occurredDate.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}
