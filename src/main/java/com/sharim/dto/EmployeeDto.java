package com.sharim.dto;

import com.sharim.entities.AuthenticationEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    String empId;

    String fName;

    String lName;

    String email;

    String username;

    AuthenticationEntity.EmployeeStatus status;
}
