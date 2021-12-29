package org.larrieulacoste.noe.al.trademe.features.payment.application;

import io.quarkus.scheduler.Scheduled;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.EventBus;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduledPaymentService {
    private final Logger logger;
    private final EventBus<ApplicationEvent> eventBus;


    public ScheduledPaymentService(EventBus<ApplicationEvent> eventBus) {
        this.eventBus = eventBus;
        logger = LoggerFactory.getLogger(this);
    }


    @Scheduled(cron = "0 ${cron.payment.monthly.hour-of-month} ${cron.payment.monthly.day-of-month} * *")
    public void monthlyPayment() {
        // TODO
        logger.log("Triggered monthly payment");
    }
}
