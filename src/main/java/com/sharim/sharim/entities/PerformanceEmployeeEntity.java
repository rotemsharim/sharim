package com.sharim.sharim.entities;

import com.sharim.sharim.converters.EmployeePerformanceStatusConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "performance_emp")
@Data
public class PerformanceEmployeeEntity {

    @EmbeddedId
    IdKey id;

    @Convert(converter = EmployeePerformanceStatusConverter.class)
    @Column(name = "status")
    boolean active;

    boolean confirm;

    Date confirmDate;

    @Embeddable
    @Data
    public static class IdKey implements Serializable {

        @ManyToOne
        @JoinColumn(name = "per_id")
        PerformanceEntity performance;

        String empId;
    }
}
