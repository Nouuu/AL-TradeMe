package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.application.exception.InvalidUserException;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.CreateTradesman;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateContractor;
import org.larrieulacoste.noe.al.trademe.features.members.application.command.UpdateTradesman;
import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MemberValidationService {
    private final Logger logger;
    private final StringValidators stringValidators;

    public MemberValidationService(Logger logger) {
        this.logger = logger;
        this.stringValidators = ValidatorsFactory.getStringValidatorsInstance();
    }

    public void validateCreateContractor(CreateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getCreateContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with contractor :\n - " + String.join("\n - ", errors)
            );
        }
    }

    public void validateCreateTradesman(CreateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getCreateTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with tradesman :\n - " + String.join("\n - ", errors)
            );
        }
    }

    private List<String> getCreateTradesmanInvalidFields(CreateTradesman tradesman) {
        List<String> errors = new ArrayList<>();
        required(tradesman.firstname, "firstname", errors);
        required(tradesman.lastname, "lastname", errors);
        password(tradesman.password, errors);
        email(tradesman.email, errors);
        return errors;
    }

    private List<String> getCreateContractorInvalidFields(CreateContractor contractorEventEntity) {
        List<String> errors = new ArrayList<>();
        required(contractorEventEntity.firstname, "firstname", errors);
        required(contractorEventEntity.lastname, "lastname", errors);
        password(contractorEventEntity.password, errors);
        email(contractorEventEntity.email, errors);
        return errors;
    }

    public void validateUpdateContractor(UpdateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getUpdateContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with contractor :\n - " + String.join("\n - ", errors)
            );
        }
    }

    public void validateUpdateTradesman(UpdateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getUpdateTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(
                    "Errors with tradesman :\n - " + String.join("\n - ", errors)
            );
        }
    }

    private List<String> getUpdateTradesmanInvalidFields(UpdateTradesman tradesman) {
        List<String> errors = new ArrayList<>();
        required(tradesman.tradesmanId, "tradesmanId", errors);
        if (tradesman.firstname != null) {
            required(tradesman.firstname, "firstname", errors);
        }
        if (tradesman.lastname != null) {
            required(tradesman.lastname, "lastname", errors);
        }
        if (tradesman.password != null) {
            password(tradesman.password, errors);
        }
        if (tradesman.email != null) {
            email(tradesman.email, errors);
        }
        return errors;
    }

    private List<String> getUpdateContractorInvalidFields(UpdateContractor contractor) {
        List<String> errors = new ArrayList<>();
        required(contractor.contractorId, "contractorId", errors);
        if (contractor.firstname != null) {
            required(contractor.firstname, "firstname", errors);
        }
        if (contractor.lastname != null) {
            required(contractor.lastname, "lastname", errors);
        }
        if (contractor.password != null) {
            password(contractor.password, errors);
        }
        if (contractor.email != null) {
            email(contractor.email, errors);
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
}
