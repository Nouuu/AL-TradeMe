package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.features.payment.infrastructure.StubPaymentApi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class APIConfiguration {

    @ApplicationScoped
    PaymentAPI paymentAPI() {
        return new StubPaymentApi();
    }
}
