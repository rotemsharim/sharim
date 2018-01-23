package com.sharim.sharim.dto;

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

}
