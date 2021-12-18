package org.larrieulacoste.noe.al.trademe.features.member_validation.application;

import org.larrieulacoste.noe.al.trademe.application.event.ContractorEventEntity;
import org.larrieulacoste.noe.al.trademe.application.event.TradesmanEventEntity;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public final class MemberValidationService {
    private final Logger logger;

    public MemberValidationService(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

/*
    public Boolean isUserValid(User user) {
        logger.log("Triggered validation with user : " + user);

        return user != null &&
                StringUtils.isNotBlank(user.getFirstname().getField()) &&
                StringUtils.isNotBlank(user.getLastname().getField()) &&
                StringUtils.isNotBlank(user.getPassword().getPasswordString()) &&
                user.getPassword().getPasswordString().length() > 8 &&
                StringUtils.isNotBlank(user.getEmail().getEmailAddressString());
    }
*/

    public List<String> getMemberInvalidFields(TradesmanEventEntity tradesmanEventEntity) {
        List<String> errors = new ArrayList<>();
        required(tradesmanEventEntity.firstname, "firstname", errors);
        required(tradesmanEventEntity.lastname, "lastname", errors);
        password(tradesmanEventEntity.password, errors);
        email(tradesmanEventEntity.email, errors);
        return errors;
    }

    public List<String> getMemberInvalidFields(ContractorEventEntity contractorEventEntity) {
        List<String> errors = new ArrayList<>();
        required(contractorEventEntity.firstname, "firstname", errors);
        required(contractorEventEntity.lastname, "lastname", errors);
        password(contractorEventEntity.password, errors);
        email(contractorEventEntity.email, errors);
        return errors;
    }

    private void required(String field, String fieldName, List<String> errors) {
        if (!StringValidators.isNotEmptyOrOnlyWhitespaces(field)) {
            errors.add(fieldName);
        }
    }

    private void password(String field, List<String> errors) {
        if (!StringValidators.isValidPassword(field)) {
            errors.add("password");
        }
    }

    private void email(String field, List<String> errors) {
        if (!StringValidators.isEmail(field)) {
            errors.add("email");
        }
    }
}
