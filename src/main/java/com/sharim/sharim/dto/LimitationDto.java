package com.sharim.sharim.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
public class LimitationDto {

    int limId;

    int limGroup;

    @NotNull
    Date startDate;

    @NotNull
    Date endDate;

    String comment;

    boolean limStatus;

    Date insertDate;

}
