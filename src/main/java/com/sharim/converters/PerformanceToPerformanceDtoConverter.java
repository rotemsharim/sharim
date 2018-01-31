package com.sharim.converters;

import com.sharim.dto.PerformanceDto;
import com.sharim.entities.PerformanceEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceToPerformanceDtoConverter implements Converter<PerformanceEntity, PerformanceDto> {


    @Override
    public PerformanceDto convert(PerformanceEntity source) {
        return PerformanceDto.builder()
                .clientId(source.getClientNum())
                .created(source.getCreated())
                .place(source.getPlace())
                .id(source.getPerId())
                .status(source.getStatus())
                .date(source.getPerformanceDate())
                .progId(source.getProgId())
                .type(source.getType()).build();

    }
}
