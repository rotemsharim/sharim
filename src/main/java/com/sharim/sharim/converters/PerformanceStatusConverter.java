package com.sharim.sharim.converters;

import com.sharim.sharim.entities.PerformanceEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.sharim.sharim.entities.PerformanceEntity.PerformanceStatus.Approved;
import static com.sharim.sharim.entities.PerformanceEntity.PerformanceStatus.Cancelled;
import static com.sharim.sharim.entities.PerformanceEntity.PerformanceStatus.Option;

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
                return Option;
            case "מאושר":
                return Approved;
            case "מבוטל":
                return Cancelled;
            default:
                return Option;
        }
    }
}
