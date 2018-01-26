package com.sharim.sharim.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class LimitationDto {

    int limId;

    int limGroup;

    Date startDate;

    Date endDate;

    String comment;

    boolean limStatus;

    Date insertDate;

}
