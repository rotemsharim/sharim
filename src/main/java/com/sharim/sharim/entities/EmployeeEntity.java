package com.sharim.sharim.entities;

import lombok.Data;

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

    String username;

}
