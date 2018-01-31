package com.sharim.sharim.dto;

import com.sharim.sharim.entities.PerformanceEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeePerformanceUpdateDto {

    Boolean confirm;

}
