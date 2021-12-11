package org.larrieulacoste.noe.al.trademe;


import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryUserRepository;
import org.larrieulacoste.noe.al.trademe.features.members.service.ContractorsService;
import org.larrieulacoste.noe.al.trademe.features.members.service.TradesmenService;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.features.membership_application.application.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.features.membership_application.service.UserApplicationService;
import org.larrieulacoste.noe.al.trademe.features.membership_validation.UserValidationService;
import org.larrieulacoste.noe.al.trademe.features.payment.infrastructure.StubPaymentApi;
import org.larrieulacoste.noe.al.trademe.features.payment.service.PaymentService;
import org.larrieulacoste.noe.al.trademe.infrastructure.logger.DefaultLoggerFactory;
import org.larrieulacoste.noe.al.trademe.kernel.event.ApplicationEvent;
import org.larrieulacoste.noe.al.trademe.kernel.event.DefaultEventBus;

public class App {
    public static void main(String[] args) {
        var loggerFactory = new DefaultLoggerFactory();

        var userRepository = new InMemoryUserRepository(loggerFactory);
        var applicationEventBus = new DefaultEventBus<ApplicationEvent>(loggerFactory);
        var paymentAPI = new StubPaymentApi();

        var userValidationService = new UserValidationService(loggerFactory);
        var userApplicationService = new UserApplicationService(applicationEventBus, loggerFactory, userValidationService);
        var contractorsService = new ContractorsService(userRepository);
        var tradesmenService = new TradesmenService(userRepository);
        var paymentService = new PaymentService(loggerFactory, paymentAPI);

        // applicationEventBus.register(NewTradesmanApplicative.class, paymentService);
        applicationEventBus.register(NewContractorApplicative.class, paymentService);
        applicationEventBus.register(NewTradesmanApplicative.class, tradesmenService.getNewContractorApplicativeListener());
        applicationEventBus.register(NewContractorApplicative.class, contractorsService.getNewContractorApplicativeListener());

        var user = User.of(userRepository.nextId(), NotEmptyString.of("larrieu"), NotEmptyString.of("noé"),
                EmailAddress.of("noe@mail.com"), Password.of("changeme123"));
        var contractor = Contractor.withUser(user);

        userApplicationService.applyForMembership(contractor, -1.);

        // System.out.println(userRepository.byId(user.getUserId()));
        // userRepository.byId(UserId.of("unknown id")); // Throws user not found exception
    }
}
