package com.sharim.converters;

import com.sharim.entities.EmployeeEntity;
import com.sharim.dto.EmployeeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeDtoConverter implements Converter<EmployeeEntity, EmployeeDto> {

    @Override
    public EmployeeDto convert(EmployeeEntity source) {

        return EmployeeDto.builder()
                .fName(source.getFName())
                .lName(source.getLName())
                .empId(source.getEmpId())
                .email(source.getEmail())
                .status(source.getStatus())
                .build();
    }
}
