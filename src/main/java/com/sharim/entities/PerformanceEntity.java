package com.sharim.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="performance")
@Data
public class PerformanceEntity {

    public enum PerformanceStatus {
        Option, Approved, Cancelled
    }

    @Id
    int perId;

    int clientNum;

    @Column(name = "per_date_time")
    Date performanceDate;

    @Column(name = "perf_status")
    PerformanceStatus status;

    Integer progId;

    String place;

    @Column(name = "open_date")
    Date created;

    String type;

}
