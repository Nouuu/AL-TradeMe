package org.larrieulacoste.noe.al.trademe.features.membership_validation;

import org.apache.commons.lang3.StringUtils;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.Objects;

public final class UserValidationService {
    private final Logger logger;

    public UserValidationService(LoggerFactory loggerFactory) {
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
}
