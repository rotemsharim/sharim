package com.sharim.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "perf_emp_confirmation")
@Data
public class EmployeeConfirmationEntity {

    @Id
    @GeneratedValue
    int confirmId;

    String empId;

    int perId;

    boolean confirmation;

    String message;

    @Column (name = "date")
    Date addedDate;

    @Column (name = "confirmdate")
    Date confirmDate;
}
