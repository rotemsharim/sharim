package com.sharim.sharim.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@Data
public class EmployeeEntity {

    @Id
    String empId;

    String fName;

    String lName;

    String email;

    @Column(name = "auth")
    AuthenticationEntity.EmployeeStatus status;

}
