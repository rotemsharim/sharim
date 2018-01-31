package com.sharim.converters;

import com.sharim.entities.PerformanceEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PerformanceStatusConverter implements AttributeConverter<PerformanceEntity.PerformanceStatus, String> {
    @Override
    public String convertToDatabaseColumn(PerformanceEntity.PerformanceStatus attribute) {
        switch (attribute) {
            case Option:
                return "אופציה";
            case Approved:
                return "מאושר";
            case Cancelled:
                return "מבוטל";
            default:
                return "";
        }
    }

    @Override
    public PerformanceEntity.PerformanceStatus convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "אופציה":
                return PerformanceEntity.PerformanceStatus.Option;
            case "מאושר":
                return PerformanceEntity.PerformanceStatus.Approved;
            case "מבוטל":
                return PerformanceEntity.PerformanceStatus.Cancelled;
            default:
                return PerformanceEntity.PerformanceStatus.Option;
        }
    }
}
