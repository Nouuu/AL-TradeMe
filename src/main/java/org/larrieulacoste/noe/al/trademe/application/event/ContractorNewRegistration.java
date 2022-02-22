package org.larrieulacoste.noe.al.trademe.application.event;

import org.larrieulacoste.noe.al.trademe.domain.model.PaymentMethod;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventId;

import java.time.ZonedDateTime;
import java.util.Objects;

public record ContractorNewRegistration(
        EventId eventId,
        ZonedDateTime occurredDate,
        String firstname,
        String lastname,
        String email,
        String password,
        PaymentMethod paymentMethod
) implements ApplicationEvent {

    public ContractorNewRegistration {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(occurredDate);
        Objects.requireNonNull(firstname);
        Objects.requireNonNull(lastname);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(paymentMethod);
    }

    public static ContractorNewRegistration of(
            String firstname,
            String lastname,
            String email,
            String password,
            PaymentMethod paymentMethod
    ) {
        return new ContractorNewRegistration(
                EventId.create(),
                ZonedDateTime.now(),
                firstname,
                lastname,
                email,
                password,
                paymentMethod
        );
    }
}
