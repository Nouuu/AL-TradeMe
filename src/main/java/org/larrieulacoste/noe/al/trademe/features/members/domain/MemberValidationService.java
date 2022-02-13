package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.domain.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesman;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.validators.PaymentInformationsValidator;
import org.larrieulacoste.noe.al.trademe.kernel.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MemberValidationService {
    private static final String STRING_DELIMITER = "\n - ";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private final Logger logger;
    private final StringValidators stringValidators;
    private final PaymentInformationsValidator paymentInformationsValidator;

    MemberValidationService(Logger logger, StringValidators stringValidators, PaymentInformationsValidator paymentInformationsValidator) {
        this.logger = logger;
        this.stringValidators = stringValidators;
        this.paymentInformationsValidator = paymentInformationsValidator;
    }

    public void validateCreateContractor(CreateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getCreateContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with contractor :\n - " + String.join(MemberValidationService.STRING_DELIMITER, errors)
            );
        }
    }

    public void validateCreateTradesman(CreateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getCreateTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with tradesman :\n - " + String.join(MemberValidationService.STRING_DELIMITER, errors)
            );
        }
    }

    private List<String> getCreateTradesmanInvalidFields(CreateTradesman tradesman) {
        List<String> errors = new ArrayList<>();
        required(tradesman.firstname(), MemberValidationService.FIRSTNAME, errors);
        required(tradesman.lastname(), MemberValidationService.LASTNAME, errors);
        password(tradesman.password(), errors);
        email(tradesman.email(), errors);
        paymentMethod(tradesman.paymentMethodType(), tradesman.paymentMethodRessource(), errors);
        return errors;
    }

    private List<String> getCreateContractorInvalidFields(CreateContractor contractor) {
        List<String> errors = new ArrayList<>();
        required(contractor.firstname(), MemberValidationService.FIRSTNAME, errors);
        required(contractor.lastname(), MemberValidationService.LASTNAME, errors);
        password(contractor.password(), errors);
        email(contractor.email(), errors);
        paymentMethod(contractor.paymentMethodType(), contractor.paymentMethodRessource(), errors);
        return errors;
    }

    public void validateUpdateContractor(UpdateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getUpdateContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with contractor :\n - " + String.join(MemberValidationService.STRING_DELIMITER, errors)
            );
        }
    }

    public void validateUpdateTradesman(UpdateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getUpdateTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with tradesman :\n - " + String.join(MemberValidationService.STRING_DELIMITER, errors)
            );
        }
    }

    private List<String> getUpdateTradesmanInvalidFields(UpdateTradesman tradesman) {
        List<String> errors = new ArrayList<>();
        required(tradesman.tradesmanId(), "tradesmanId", errors);
        if (tradesman.firstname() != null) {
            required(tradesman.firstname(), MemberValidationService.FIRSTNAME, errors);
        }
        if (tradesman.lastname() != null) {
            required(tradesman.lastname(), MemberValidationService.LASTNAME, errors);
        }
        if (tradesman.password() != null) {
            password(tradesman.password(), errors);
        }
        if (tradesman.email() != null) {
            email(tradesman.email(), errors);
        }
        return errors;
    }

    private List<String> getUpdateContractorInvalidFields(UpdateContractor contractor) {
        List<String> errors = new ArrayList<>();
        required(contractor.contractorId(), "contractorId", errors);
        if (contractor.firstname() != null) {
            required(contractor.firstname(), MemberValidationService.FIRSTNAME, errors);
        }
        if (contractor.lastname() != null) {
            required(contractor.lastname(), MemberValidationService.LASTNAME, errors);
        }
        if (contractor.password() != null) {
            password(contractor.password(), errors);
        }
        if (contractor.email() != null) {
            email(contractor.email(), errors);
        }
        return errors;
    }


    private void required(String field, String fieldName, List<String> errors) {
        if (!stringValidators.isNotEmptyOrOnlyWhitespaces(field)) {
            errors.add(fieldName);
        }
    }

    private void password(String field, List<String> errors) {
        if (!stringValidators.isValidPassword(field)) {
            errors.add("password");
        }
    }

    private void email(String field, List<String> errors) {
        if (!stringValidators.isEmail(field)) {
            errors.add("email");
        }
    }

    private void paymentMethod(String paymentMethodType, String paymentMethodRessource, List<String> errors) {
        if (!paymentInformationsValidator.isValidPaymentMethod(paymentMethodType, paymentMethodRessource)) {
            errors.add("payment method");
        }
    }
}
