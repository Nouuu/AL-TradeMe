package org.larrieulacoste.noe.al.trademe;


import org.larrieulacoste.noe.al.trademe.application.event.NewContractorApplicative;
import org.larrieulacoste.noe.al.trademe.application.event.NewContractorRegistration;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanApplicative;
import org.larrieulacoste.noe.al.trademe.application.event.NewTradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.domain.entity.Contractor;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.model.EmailAddress;
import org.larrieulacoste.noe.al.trademe.domain.model.NotEmptyString;
import org.larrieulacoste.noe.al.trademe.domain.model.Password;
import org.larrieulacoste.noe.al.trademe.features.member_application.service.MemberApplicationService;
import org.larrieulacoste.noe.al.trademe.features.member_registration.service.MemberRegistrationService;
import org.larrieulacoste.noe.al.trademe.features.member_validation.service.MemberValidationService;
import org.larrieulacoste.noe.al.trademe.features.members.infrastructure.InMemoryUserRepository;
import org.larrieulacoste.noe.al.trademe.features.members.service.ContractorsService;
import org.larrieulacoste.noe.al.trademe.features.members.service.TradesmenService;
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

        var memberValidationService = new MemberValidationService(loggerFactory);
        var memberApplicationService = new MemberApplicationService(applicationEventBus, loggerFactory, memberValidationService);
        var memberRegistrationService = new MemberRegistrationService(applicationEventBus, loggerFactory, memberValidationService);
        var paymentService = new PaymentService(loggerFactory, paymentAPI);
        var contractorsService = new ContractorsService(userRepository);
        var tradesmenService = new TradesmenService(userRepository);

        applicationEventBus.register(NewContractorApplicative.class, paymentService.getNewContractorApplicativeListener());
        applicationEventBus.register(NewTradesmanApplicative.class, paymentService.getNewTradesmanApplicativeListener());

        applicationEventBus.register(NewContractorRegistration.class, contractorsService.getNewContractorRegistrationListener());
        applicationEventBus.register(NewTradesmanRegistration.class, tradesmenService.getNewTradesmanRegistrationListener());

        var user = User.of(userRepository.nextId(), NotEmptyString.of("larrieu"), NotEmptyString.of("noé"),
                EmailAddress.of("noe@mail.com"), Password.of("changeme123"));
        var contractor = Contractor.withUser(user);

        memberApplicationService.applyForMembership(contractor, -1.);

        // userRepository.byId(UserId.of("unknown id")); // Throws user not found exception
    }
}
