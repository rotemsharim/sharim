package com.sharim.converters;

import com.sharim.dto.LimitationDto;
import com.sharim.entities.LimitationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LimitationToLimitationDtoConverter implements Converter<LimitationEntity, LimitationDto> {
    @Override
    public LimitationDto convert(LimitationEntity source) {
        return LimitationDto.builder()
                .limId(source.getLimId())
                .limGroup(source.getLimGroup())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .comment(source.getComment())
                .insertDate(source.getInsertDate())
                .limStatus(source.isLimStatus())
                .build();
    }
}
