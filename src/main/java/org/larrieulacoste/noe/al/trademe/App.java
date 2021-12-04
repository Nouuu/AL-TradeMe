package org.larrieulacoste.noe.al.trademe;


import org.larrieulacoste.noe.al.trademe.application.UserApplicationEvent;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.service.PaymentService;
import org.larrieulacoste.noe.al.trademe.domain.service.UserApplicationService;
import org.larrieulacoste.noe.al.trademe.domain.service.UserValidationService;
import org.larrieulacoste.noe.al.trademe.infrastructure.api.StubPaymentApi;
import org.larrieulacoste.noe.al.trademe.infrastructure.logger.DefaultLoggerFactory;
import org.larrieulacoste.noe.al.trademe.infrastructure.repository.InMemoryUserRepository;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;

public class App {
    public static void main(String[] args) {
        var loggerFactory = new DefaultLoggerFactory();

        var userRepository = new InMemoryUserRepository(loggerFactory);
        var applicationEventBus = new DefaultEventBus<ApplicationEvent>(loggerFactory);
        var paymentAPI = new StubPaymentApi();

        var userValidationService = new UserValidationService(loggerFactory);
        var userApplicationService = new UserApplicationService(applicationEventBus, loggerFactory, userRepository, userValidationService);
        var paymentService = new PaymentService(loggerFactory, paymentAPI);

        applicationEventBus.register(UserApplicationEvent.class, paymentService);

        var user = User.of(userRepository.nextId(), "larrieu", "noé", "noe@mail.com", "changeme123", "1111-3323-5555");
        userApplicationService.applyForMembership(user, -1.);

        System.out.println(userRepository.byId(user.getUserId()));
        // userRepository.byId(UserId.of("unknown id")); // Throws user not found exception
    }
}
