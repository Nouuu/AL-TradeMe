package org.larrieulacoste.noe.al.trademe.features.members.domain;

import org.larrieulacoste.noe.al.trademe.kernel.validators.ValidatorsAccessor;

public final class NotEmptyString {
    public final String value;

    private NotEmptyString(String value) {
        if (!ValidatorsAccessor.getStringValidatorsInstance().isNotEmptyOrOnlyWhitespaces(value)) {
            throw new IllegalArgumentException("Field must not be empty");
        }
        this.value = value;
    }

    public static NotEmptyString of(String value) {
        return new NotEmptyString(value);
    }

    @Override
    public String toString() {
        return "NotEmptyString{" +
                "field='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof NotEmptyString))
            return false;

        NotEmptyString that = (NotEmptyString) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
