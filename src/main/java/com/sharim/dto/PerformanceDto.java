package com.sharim.dto;

import com.sharim.entities.PerformanceEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PerformanceDto {

    int id;

    int clientId;

    Date date;

    PerformanceEntity.PerformanceStatus status;

    Integer progId;

    String place;

    Date created;

    String type;
}
