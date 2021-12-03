package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.domain.entity.User;

import java.time.ZonedDateTime;
import java.util.Objects;

public class UserApplicationEvent implements Event<User> {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final User user;
    private final Double amount;

    private UserApplicationEvent(EventId eventId, ZonedDateTime occurredDate, User user, Double amount) {
        this.eventId = Objects.requireNonNull(eventId);
        this.occurredDate = Objects.requireNonNull(occurredDate);
        this.user = Objects.requireNonNull(user);
        this.amount = amount;
    }

    public static UserApplicationEvent withUserAndAmount(User user, Double amount) {
        return new UserApplicationEvent(EventId.create(), ZonedDateTime.now(), user, amount);
    }

    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    @Override
    public User getItem() {
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
}
