package org.larrieulacoste.noe.al.trademe.features.payment.application;

import io.quarkus.scheduler.Scheduled;
import org.larrieulacoste.noe.al.trademe.application.event.MonthlySubscriptionPayment;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduledPaymentService {
    private final Logger logger;
    private final EventBus<ApplicationEvent> eventBus;


    ScheduledPaymentService(EventBus<ApplicationEvent> eventBus) {
        this.eventBus = eventBus;
        logger = LoggerFactory.getLoggerStatic(this);
    }


    @Scheduled(cron = "0 ${cron.payment.monthly.hour-of-month} ${cron.payment.monthly.day-of-month} * *")
    public void monthlyPayment() {
        eventBus.publish(MonthlySubscriptionPayment.create());
    }
}
