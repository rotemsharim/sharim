package com.sharim.sharim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class LimitationGroupDto {

    int limGroupId;

    @NotEmpty
    List<LimitationDto> limitationList;
}

