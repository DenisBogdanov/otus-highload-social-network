package com.bogdanium.social.network.db.entities.enums;

public enum Gender {
    MALE,
    FEMALE,
    OTHER,
    NOT_SPECIFIED;

    public static Gender get(String value) {
        if (value == null) return NOT_SPECIFIED;

        if (value.equalsIgnoreCase(MALE.name())) return MALE;
        if (value.equalsIgnoreCase(FEMALE.name())) return FEMALE;
        if (value.equalsIgnoreCase(OTHER.name())) return OTHER;

        throw new IllegalArgumentException("Invalid gender " + value);
    }
}
