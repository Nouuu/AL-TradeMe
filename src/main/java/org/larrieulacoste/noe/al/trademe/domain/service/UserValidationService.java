package org.larrieulacoste.noe.al.trademe.domain.service;

import org.apache.commons.lang3.StringUtils;
import org.larrieulacoste.noe.al.trademe.domain.entity.User;
import org.larrieulacoste.noe.al.trademe.domain.logger.Logger;
import org.larrieulacoste.noe.al.trademe.domain.logger.LoggerFactory;

import java.util.Objects;

public class UserValidationService {
    private final Logger logger;

    public UserValidationService(LoggerFactory loggerFactory) {
        this.logger = Objects.requireNonNull(loggerFactory).getLogger(this);
    }

    public Boolean isUserValid(User user) {
        logger.log("Triggered validation with user : " + user);

        return user != null &&
                StringUtils.isNotBlank(user.getFirstname()) &&
                StringUtils.isNotBlank(user.getLastname()) &&
                StringUtils.isNotBlank(user.getPassword()) &&
                user.getPassword().length() > 8 &&
                StringUtils.isNotBlank(user.getEmail()) &&
                user.getBankAccount().matches("^\\d{4}-\\d{4}-\\d{4}$");
    }
}
