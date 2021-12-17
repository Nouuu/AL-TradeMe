package org.larrieulacoste.noe.al.trademe.features.member_validation.application;

import org.apache.commons.lang3.StringUtils;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;
import org.larrieulacoste.noe.al.trademe.domain.model.ContractorRegistration;
import org.larrieulacoste.noe.al.trademe.domain.model.TradesmanRegistration;
import org.larrieulacoste.noe.al.trademe.domain.validators.StringValidators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MemberValidationService {
    private final Logger logger;

    public MemberValidationService(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    public Boolean isUserValid(User user) {
        logger.log("Triggered validation with user : " + user);

        return user != null &&
                StringUtils.isNotBlank(user.getFirstname().getField()) &&
                StringUtils.isNotBlank(user.getLastname().getField()) &&
                StringUtils.isNotBlank(user.getPassword().getPasswordString()) &&
                user.getPassword().getPasswordString().length() > 8 &&
                StringUtils.isNotBlank(user.getEmail().getEmailAddressString());
    }

    public List<String> getMemberInvalidFields(TradesmanRegistration tradesmanRegistration) {
        List<String> errors = new ArrayList<>();
        required(tradesmanRegistration.getFirstname(), "firstname", errors);
        required(tradesmanRegistration.getLastname(), "lastname", errors);
        password(tradesmanRegistration.getPassword(), errors);
        email(tradesmanRegistration.getEmail(), errors);
        return errors;
    }

    public List<String> getMemberInvalidFields(ContractorRegistration contractorRegistration) {
        List<String> errors = new ArrayList<>();
        required(contractorRegistration.getFirstname(), "firstname", errors);
        required(contractorRegistration.getLastname(), "lastname", errors);
        password(contractorRegistration.getPassword(), errors);
        email(contractorRegistration.getEmail(), errors);
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
