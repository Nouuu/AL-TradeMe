package org.larrieulacoste.noe.al.trademe.features.payment.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.query.DefaultQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import java.util.Map;

public class DefaultPaymentQueryBus extends DefaultQueryBus implements PaymentQueryBus {
    public DefaultPaymentQueryBus(Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap) {
        super(queryMap);
    }
}
