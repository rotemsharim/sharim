package com.sharim.sharim.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class LimitationDto {

    int limId;

    Date startDate;

    Date endDate;


}
