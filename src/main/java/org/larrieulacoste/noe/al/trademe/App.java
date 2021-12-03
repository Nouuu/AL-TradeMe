package org.larrieulacoste.noe.al.trademe;


import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.event.UserApplicationEvent;
import org.larrieulacoste.noe.al.trademe.domain.service.PaymentService;
import org.larrieulacoste.noe.al.trademe.domain.service.UserApplicationService;
import org.larrieulacoste.noe.al.trademe.domain.service.UserValidationService;
import org.larrieulacoste.noe.al.trademe.infrastructure.api.StubPaymentApi;
import org.larrieulacoste.noe.al.trademe.infrastructure.event.DefaultEventBus;
import org.larrieulacoste.noe.al.trademe.infrastructure.logger.DefaultLoggerFactory;
import org.larrieulacoste.noe.al.trademe.infrastructure.repository.InMemoryUserRepository;

public class App {
    public static void main(String[] args) {
        var loggerFactory = new DefaultLoggerFactory();

        var userRepository = new InMemoryUserRepository(loggerFactory);
        var userApplicationEventBus = new DefaultEventBus<UserApplicationEvent>(loggerFactory);
        var paymentAPI = new StubPaymentApi();

        var userValidationService = new UserValidationService(loggerFactory);
        var userApplicationService = new UserApplicationService(userApplicationEventBus, loggerFactory, userRepository, userValidationService);
        var paymentService = new PaymentService(loggerFactory, paymentAPI);

        userApplicationEventBus.registerSubscriber(paymentService);

        var user = User.of(userRepository.nextId(), "larrieu", "noé", "noe@mail.com", "changeme123", "1111-3323-5555");
        userApplicationService.applyForMembership(user, -1.);

        System.out.println(userRepository.byId(user.getUserId()));
        // userRepository.byId(UserId.of("unknown id")); // Throws user not found exception
    }
}
