package com.sharim.sharim.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
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

    Date confirmDate;
}
