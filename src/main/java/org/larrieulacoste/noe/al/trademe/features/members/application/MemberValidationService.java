package org.larrieulacoste.noe.al.trademe.features.members.application;

import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;
import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class MemberValidationService {
    private final Logger logger;
    private final StringValidators stringValidators;

    public MemberValidationService(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
        this.stringValidators = ValidatorsFactory.getStringValidatorsInstance();
    }

    public boolean isTradesmanValid(CreateTradesman tradesman) {
        logger.log("Triggered validation with tradesman : " + tradesman);
        List<String> errors = getTradesmanInvalidFields(tradesman);
        if (!errors.isEmpty()) {
            logger.error("Errors with tradesman :\n - " + String.join("\n - ", errors));
            return false;
        }
        return true;
    }

    public boolean isContractorValid(CreateContractor contractor) {
        logger.log("Triggered validation with contractor : " + contractor);
        List<String> errors = getContractorInvalidFields(contractor);
        if (!errors.isEmpty()) {
            logger.error("Errors with contractor :\n - " + String.join("\n - ", errors));
            return false;
        }
        return true;
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
