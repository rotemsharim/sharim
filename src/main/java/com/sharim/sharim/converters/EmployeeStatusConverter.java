package com.sharim.sharim.converters;

import com.sharim.sharim.entities.AuthenticationEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.sharim.sharim.entities.AuthenticationEntity.EmployeeStatus.*;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<AuthenticationEntity.EmployeeStatus, String> {
    @Override
    public String convertToDatabaseColumn(AuthenticationEntity.EmployeeStatus attribute) {
        switch (attribute) {
            case Manager:
                return "מנהל";
            case Regular:
                return "עובד";
            case NotActive:
                return "מוקפא";
            default:
                return "";
        }
    }

    @Override
    public AuthenticationEntity.EmployeeStatus convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "מנהל":
            case "מפתח":
                return Manager;
            case "עובד":
                return Regular;
            case "מוקפא":
                return NotActive;
            default:
                return Regular;
        }
    }
}
