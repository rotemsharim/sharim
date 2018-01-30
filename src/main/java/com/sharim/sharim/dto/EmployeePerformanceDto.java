package com.sharim.sharim.dto;

import com.sharim.sharim.entities.PerformanceEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeePerformanceDto{

    int id;

    Date date;

    PerformanceEntity.PerformanceStatus status;

    Integer progId;

    String place;

    String type;

    boolean confirm;

    Date confirmDate;

}
