package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.EntityId;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Location;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.TradesmanProfessionalAbilities;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanRegistered(
        EventId eventId,
        ZonedDateTime occurredDate,
        EntityId tradesmanId,
        String firstname,
        String lastname,
        String email,
        PaymentMethod paymentMethod,
        Location address,
        TradesmanProfessionalAbilities professionalAbilities
) implements ApplicationEvent {

    public TradesmanRegistered {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(tradesmanId);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(address);
        Objects.requireNonNull(professionalAbilities);
    }

    public static TradesmanRegistered of(
            EntityId tradesmanId,
            String firstname,
            String lastname,
            String email,
            PaymentMethod paymentMethod,
            Location address,
            TradesmanProfessionalAbilities professionalAbilities
    ) {
        return new TradesmanRegistered(
                EventId.create(),
                ZonedDateTime.now(),
                tradesmanId,
                firstname,
                lastname,
                email,
                paymentMethod,
                address,
                professionalAbilities
        );
    }
}
