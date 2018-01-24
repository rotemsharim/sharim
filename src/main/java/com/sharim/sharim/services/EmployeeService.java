package com.sharim.sharim.services;

import com.sharim.sharim.entities.Employee;
import com.sharim.sharim.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findOne(String id) {
        return employeeRepository.findOne(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
