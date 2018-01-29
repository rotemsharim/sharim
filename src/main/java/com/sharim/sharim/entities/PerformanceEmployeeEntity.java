package com.sharim.sharim.entities;

import com.sharim.sharim.converters.EmployeePerformanceStatusConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "performance_emp")
@Data
public class PerformanceEmployeeEntity {

    @EmbeddedId
    IdKey id;

    @Convert(converter = EmployeePerformanceStatusConverter.class)
    @Column(name = "status")
    boolean active;


    @Embeddable
    @Data
    public static class IdKey implements Serializable {

        @ManyToOne
        @JoinColumn(name = "per_id")
        PerformanceEntity performance;

        String empId;
    }
}
