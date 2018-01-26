package com.sharim.sharim.converters;

import javax.persistence.AttributeConverter;

public class EmployeeStatusConverter implements AttributeConverter<Boolean, String> {

    private static final String ACTIVE = "פעיל";
    private static final String CANCELLED = "מבוטל";

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute) {
            return ACTIVE;
        }

        return CANCELLED;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case ACTIVE:
                return true;
            case CANCELLED:
                return false;
            default:
                return false;
        }
    }
}
