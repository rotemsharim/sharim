package com.sharim.sharim.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LimitationGroupDto {

    int limGroupId;

    String empId;

    List<LimitationDto> limitationList;
}

