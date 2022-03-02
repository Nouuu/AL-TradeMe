package org.larrieulacoste.noe.al.trademe.domain.event;

import org.larrieulacoste.noe.al.trademe.shared_kernel.model.Location;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.shared_kernel.model.TradesmanProfessionalAbilities;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record TradesmanNewRegistration(
        EventId eventId,
        ZonedDateTime occurredDate,
        String firstname,
        String lastname,
        String email,
        String password,
        PaymentMethod paymentMethod,
        Location address,
        TradesmanProfessionalAbilities professionalAbilities
) implements ApplicationEvent {

    public TradesmanNewRegistration {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(address);
        Objects.requireNonNull(professionalAbilities);
    }

    public static TradesmanNewRegistration of(
            String firstname,
            String lastname,
            String email,
            String password,
            PaymentMethod paymentMethod,
            Location address,
            TradesmanProfessionalAbilities professionalAbilities
    ) {
        return new TradesmanNewRegistration(
                EventId.create(),
                ZonedDateTime.now(),
                firstname,
                lastname,
                email,
                password,
                paymentMethod,
                address,
                professionalAbilities
        );
    }

}
