package org.larrieulacoste.noe.al.trademe.domain.validators;

import org.apache.commons.lang3.StringUtils;

public class RequiredValidator {
    private RequiredValidator() {
    }

    public static boolean isValid(Object item) {
        return item != null;
    }

    public static boolean isValid(String item) {
        return StringUtils.isNotBlank(item);
    }
}
