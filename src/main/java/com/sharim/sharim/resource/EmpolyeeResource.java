package com.sharim.sharim.resource;

import com.sharim.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.sharim.dto.EmployeeDto;
import com.sharim.sharim.entities.Employee;
import com.sharim.sharim.services.EmployeeService;
import com.sharim.sharim.services.LimitationService;
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
    EmployeeService employeeService;

    @Autowired
    LimitationService limitationService;

    @Autowired
    EmployeeToEmployeeDtoConverter employeeToEmployeeDtoConverter;

    @RequestMapping
    public @ResponseBody List<EmployeeDto> allEmployees() {
        return employeeService.findAll()
                .stream().map(e -> employeeToEmployeeDtoConverter.convert(e))
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public @ResponseBody EmployeeDto findEmployee(@PathVariable("id") String id) throws Exception {
        Employee employee = employeeService.findOne(id);
        if (employee == null) {
            return null;
        }
        return employeeToEmployeeDtoConverter.convert(employee);
    }



}
