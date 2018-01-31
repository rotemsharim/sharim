package com.sharim.converters;

import com.sharim.dto.LimitationDto;
import com.sharim.entities.LimitationEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LimitationDtoToLimitationConverter implements Converter<LimitationDto, LimitationEntity> {
    @Override
    public LimitationEntity convert(LimitationDto source) {
        return LimitationEntity.builder()
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
