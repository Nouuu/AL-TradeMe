package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public record TradesmenSubscriptionPendingPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        List<TradesmanEventEntity> tradesmen
) implements ApplicationEvent {

    public TradesmenSubscriptionPendingPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmen);
    }

    public static TradesmenSubscriptionPendingPayment withTradesmen(List<TradesmanEventEntity> tradesmen) {
        return new TradesmenSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), tradesmen);
    }

    @Override
    public List<TradesmanEventEntity> tradesmen() {
        return List.copyOf(tradesmen);
    }

}
