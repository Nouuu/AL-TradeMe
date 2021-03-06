package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Location;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.TradesmanProfessionalAbilities;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanUpdated(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId tradesmanId,
        String firstname,
        String lastname,
        String email,
        PaymentMethod paymentMethod,
        Location address,
        TradesmanProfessionalAbilities professionalAblilites
) implements ApplicationEvent {

    public TradesmanUpdated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmanId);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(address);
        Objects.requireNonNull(professionalAblilites);

    }

    public static TradesmanUpdated of(
            EntityId tradesmanId,
            String firstname,
            String lastname,
            String email,
            PaymentMethod paymentMethod,
            Location address,
            TradesmanProfessionalAbilities professionalAblilites
    ) {
        return new TradesmanUpdated(
                EventId.create(),
                ZonedDateTime.now(),
                tradesmanId,
                firstname,
                lastname,
                email,
                paymentMethod,
                address,
                professionalAblilites
        );
    }

}
