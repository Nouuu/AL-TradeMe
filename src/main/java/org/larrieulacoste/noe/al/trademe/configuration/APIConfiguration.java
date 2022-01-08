package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.features.payment.api.PaymentAPI;
import org.larrieulacoste.noe.al.trademe.features.payment.infrastructure.StubPaymentApi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Dependent
final class APIConfiguration {

    @Produces
    @Singleton
    PaymentAPI paymentAPI() {
        return new StubPaymentApi();
    }
}
