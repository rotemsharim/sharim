package com.sharim.sharim.resource.employee;

import com.sharim.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.sharim.converters.LimitationDtoToLimitationConverter;
import com.sharim.sharim.converters.LimitationToLimitationDtoConverter;
import com.sharim.sharim.converters.PerformanceToPerformanceDtoConverter;
import com.sharim.sharim.dto.LimitationDto;
import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.EmployeeEntity;
import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.exceptions.NotFoundException;
import com.sharim.sharim.services.EmployeeService;
import com.sharim.sharim.services.LimitationService;
import com.sharim.sharim.services.PerformanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeToEmployeeDtoConverter employeeToEmployeeDtoConverter;

    @RequestMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> allEmployees() {
        Optional<List<EmployeeEntity>> employeeList = employeeService.findAll();

        if (!employeeList.isPresent()) {
            return new ResponseEntity<>("no employees", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employeeService.findAll().get()
                .stream().map(e -> employeeToEmployeeDtoConverter.convert(e))
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @RequestMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> findEmployee(@PathVariable("id") String id) throws Exception, NotFoundException {
        Optional<EmployeeEntity> employee = employeeService.findOne(id);
        if (!employee.isPresent()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(employeeToEmployeeDtoConverter.convert(employee.get()), HttpStatus.OK);
    }








}
