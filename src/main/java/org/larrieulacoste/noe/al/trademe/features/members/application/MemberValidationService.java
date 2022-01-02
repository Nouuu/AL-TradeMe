package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;
import org.larrieulacoste.noe.al.trademe.kernel.logger.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MemberValidationService {
    private final Logger logger;
    private final StringValidators stringValidators;

    public MemberValidationService() {
        this.logger = LoggerFactory.getLogger(this);
        this.stringValidators = ValidatorsFactory.getStringValidatorsInstance();
    }

    public void validateContractor(CreateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with contractor :\n - " + String.join("\n - ", errors)
            );
        }
    }

    public void validateTradesman(CreateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with tradesman :\n - " + String.join("\n - ", errors)
            );
        }
    }

    private List<String> getTradesmanInvalidFields(CreateTradesman tradesman) {
        List<String> errors = new ArrayList<>();
        required(tradesman.firstname, "firstname", errors);
        required(tradesman.lastname, "lastname", errors);
        password(tradesman.password, errors);
        email(tradesman.email, errors);
        return errors;
    }

    private List<String> getContractorInvalidFields(CreateContractor contractorEventEntity) {
        List<String> errors = new ArrayList<>();
        required(contractorEventEntity.firstname, "firstname", errors);
        required(contractorEventEntity.lastname, "lastname", errors);
        password(contractorEventEntity.password, errors);
        email(contractorEventEntity.email, errors);
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
}
