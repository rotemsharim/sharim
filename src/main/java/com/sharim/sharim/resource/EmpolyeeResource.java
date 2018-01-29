package com.sharim.sharim.resource;

import com.sharim.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.sharim.converters.LimitationToLimitationDtoConverter;
import com.sharim.sharim.converters.PerformanceToPerformanceDtoConverter;
import com.sharim.sharim.entities.EmployeeEntity;
import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.repository.PerformanceEmployeeRepository;
import com.sharim.sharim.services.EmployeeService;
import com.sharim.sharim.services.LimitationService;
import com.sharim.sharim.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmpolyeeResource {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    LimitationService limitationService;

    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeeToEmployeeDtoConverter employeeToEmployeeDtoConverter;

    @Autowired
    LimitationToLimitationDtoConverter limitationToLimitationDtoConverter;

    @Autowired
    PerformanceToPerformanceDtoConverter performanceToPerformanceDtoConverter;

    @RequestMapping
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
    public ResponseEntity<?> findEmployee(@PathVariable("id") String id) throws Exception {
        Optional<EmployeeEntity> employee = employeeService.findOne(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>("employee doesn't exist", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeToEmployeeDtoConverter.convert(employee.get()), HttpStatus.OK);
    }


    @RequestMapping("/{id}/limitations")
    public ResponseEntity<?> getLimitations(@PathVariable("id") String id,
                                            @RequestParam(required = true) Long from,
                                            @RequestParam(required = true) Long to) throws Exception {

        Optional<List<LimitationEntity>> limitationList = limitationService.findByEmpIdBetweenStartDateAndEndDate(id, new Date(from), new Date(to));

        if (!limitationList.isPresent()) {
            return new ResponseEntity<>("no limitations for employee between these dates", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(limitationList.get().stream()
                .map(l -> limitationToLimitationDtoConverter.convert(l))
                .collect(Collectors.toList()), HttpStatus.OK);

    }


    @RequestMapping("/{id}/performances")
    public ResponseEntity<?> getPerformance(@PathVariable("id") String id,
                                            @RequestParam(required = true) Long from,
                                            @RequestParam(required = true) Long to) throws Exception {


        Optional<List<PerformanceEntity>> performanceList = performanceService.findByEmpIdAndStartDateRange(id, new Date(from), new Date(to));

        if (!performanceList.isPresent() || performanceList.get().size() == 0) {
            return new ResponseEntity<>("no limitations for employee between these dates", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(performanceList.get().stream()
                .map(p -> performanceToPerformanceDtoConverter.convert(p))
                .collect(Collectors.toList()), HttpStatus.OK);
    }


}
