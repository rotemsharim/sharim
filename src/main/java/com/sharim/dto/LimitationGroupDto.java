package com.sharim.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class LimitationGroupDto {

    int limGroupId;

    @NotEmpty
    List<LimitationDto> limitationList;
}

