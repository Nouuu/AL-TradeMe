package org.larrieulacoste.noe.al.trademe.domain.model;

import org.larrieulacoste.noe.al.trademe.domain.validators.ValidatorsFactory;

public final class NotEmptyString {
    private final String field;

    private NotEmptyString(String field) {
        if (!ValidatorsFactory.getStringValidatorsInstance().isNotEmptyOrOnlyWhitespaces(field)) {
            throw new IllegalArgumentException("Field must not be empty");
        }
        this.field = field;
    }

    public static NotEmptyString of(String value) {
        return new NotEmptyString(value);
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return "NotEmptyString{" +
                "field='" + field + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotEmptyString)) return false;

        NotEmptyString that = (NotEmptyString) o;

        return field.equals(that.field);
    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }
}
