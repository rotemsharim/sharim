package com.sharim.sharim.resource;

import com.sharim.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.sharim.dto.EmployeeDto;
import com.sharim.sharim.entities.Employee;
import com.sharim.sharim.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmpolyeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeToEmployeeDtoConverter employeeToEmployeeDtoConverter;

    @RequestMapping
    public @ResponseBody List<EmployeeDto> allEmployees() {
        return employeeRepository.findAll()
                .stream().map(e -> employeeToEmployeeDtoConverter.convert(e))
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public @ResponseBody EmployeeDto findEmployee(@PathVariable("id") String id) {
        return employeeToEmployeeDtoConverter.convert(employeeRepository.getOne(id));
    }

}
