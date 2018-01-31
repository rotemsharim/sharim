package com.sharim.converters;

import com.sharim.entities.AuthenticationEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<AuthenticationEntity.EmployeeStatus, String> {
    @Override
    public String convertToDatabaseColumn(AuthenticationEntity.EmployeeStatus attribute) {
        switch (attribute) {
            case Admin:
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
                return AuthenticationEntity.EmployeeStatus.Admin;
            case "עובד":
                return AuthenticationEntity.EmployeeStatus.Regular;
            case "מוקפא":
                return AuthenticationEntity.EmployeeStatus.NotActive;
            default:
                return AuthenticationEntity.EmployeeStatus.Regular;
        }
    }
}
