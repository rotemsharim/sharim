package com.sharim.resource.employee;

import com.sharim.entities.EmployeeEntity;
import com.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.exceptions.NotFoundException;
import com.sharim.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
