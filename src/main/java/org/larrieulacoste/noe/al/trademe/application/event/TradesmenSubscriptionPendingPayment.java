package org.larrieulacoste.noe.al.trademe.application.event;

import java.util.ArrayList;
import org.larrieulacoste.noe.al.trademe.domain.model.MemberPayment;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public record TradesmenSubscriptionPendingPayment(
        EventId eventId,
        ZonedDateTime occurredDate,
        List<MemberPayment> tradesmen
) implements ApplicationEvent {

    public TradesmenSubscriptionPendingPayment {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmen);
    }

    public static TradesmenSubscriptionPendingPayment withTradesmen(List<MemberPayment> tradesmen) {
        return new TradesmenSubscriptionPendingPayment(EventId.create(), ZonedDateTime.now(), tradesmen);
    }

    @Override
    public List<MemberPayment> tradesmen() {
        return new ArrayList<>(tradesmen);
    }
}
