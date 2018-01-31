package com.sharim.converters;

import com.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.dto.EmployeePerformanceDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeePerformanceToEmployeePerformanceDtoConverter implements Converter<PerformanceEmployeeEntity, EmployeePerformanceDto> {

    @Override
    public EmployeePerformanceDto convert(PerformanceEmployeeEntity source) {
        return EmployeePerformanceDto.builder()
                .place(source.getId().getPerformance().getPlace())
                .id(source.getId().getPerformance().getPerId())
                .status(source.getId().getPerformance().getStatus())
                .date(source.getId().getPerformance().getPerformanceDate())
                .progId(source.getId().getPerformance().getProgId())
                .confirm(source.isConfirm())
                .confirmDate(source.getConfirmDate())
                .type(source.getId().getPerformance().getType()).build();

    }

}
