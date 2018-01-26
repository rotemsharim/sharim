package com.sharim.sharim.services;

import com.sharim.sharim.entities.EmployeeEntity;
import com.sharim.sharim.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<EmployeeEntity> findOne(String id) {
        return Optional.ofNullable(employeeRepository.findOne(id));
    }

    public Optional<List<EmployeeEntity>> findAll() {
        return Optional.ofNullable(employeeRepository.findAll());
    }
}
