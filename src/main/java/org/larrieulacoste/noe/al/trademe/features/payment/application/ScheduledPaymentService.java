package org.larrieulacoste.noe.al.trademe.features.payment.application;

import io.quarkus.scheduler.Scheduled;
import org.larrieulacoste.noe.al.trademe.application.event.MonthlySubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduledPaymentService {
    private final Logger logger;
    private final EventBus<ApplicationEvent> eventBus;

    ScheduledPaymentService(Logger logger, EventBus<ApplicationEvent> eventBus) {
        this.logger = logger;
        this.eventBus = eventBus;
    }

    @Scheduled(cron = "0 ${cron.payment.monthly.hour-of-month} ${cron.payment.monthly.day-of-month} * *")
    public void monthlyPayment() {
        logger.log("Triggering monthly payments");
        eventBus.publish(MonthlySubscriptionPayment.create());
    }
}
