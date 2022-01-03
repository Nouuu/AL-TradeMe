package org.larrieulacoste.noe.al.trademe.features.invoices.kernel;

import org.larrieulacoste.noe.al.trademe.kernel.query.DefaultQueryBus;
import org.larrieulacoste.noe.al.trademe.kernel.query.Query;
import org.larrieulacoste.noe.al.trademe.kernel.query.QueryHandler;

import java.util.Map;

public class DefaultInvoicesQueryBus extends DefaultQueryBus implements InvoicesQueryBus {
    public DefaultInvoicesQueryBus(Map<Class<? extends Query>, QueryHandler<? extends Query, ?>> queryMap) {
        super(queryMap);
    }
}