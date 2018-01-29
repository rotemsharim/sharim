package com.sharim.sharim.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@Data
public class AuthenticationEntity {

    public enum EmployeeStatus {
        Manager, Regular, NotActive
    }

    @Id
    String empId;

    String username;

    String password;

    @Column(name = "auth")
    EmployeeStatus status;


}
