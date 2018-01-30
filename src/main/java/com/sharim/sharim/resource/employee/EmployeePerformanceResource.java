package com.sharim.sharim.resource.employee;

import com.sharim.sharim.converters.EmployeePerformanceToEmployeePerformanceDtoConverter;
import com.sharim.sharim.converters.PerformanceToPerformanceDtoConverter;
import com.sharim.sharim.dto.EmployeePerformanceDto;
import com.sharim.sharim.dto.PerformanceDto;
import com.sharim.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employees/{id}")
public class EmployeePerformanceResource {


    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeePerformanceToEmployeePerformanceDtoConverter employeePerformanceToEmployeePerformanceDtoConverter;


    @RequestMapping("performances")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> getPerformance(@PathVariable("id") String id,
                                            @RequestParam(required = true) Long from,
                                            @RequestParam(required = true) Long to) throws Exception {


        Optional<List<PerformanceEmployeeEntity>> performanceList = performanceService.findByEmpIdAndStartDateRange(id, new Date(from), new Date(to));

        if (!performanceList.isPresent() || performanceList.get().size() == 0) {
            return new ResponseEntity<>("no limitations for employee between these dates", HttpStatus.NO_CONTENT);
        }

        List<EmployeePerformanceDto> performanceDtoList = performanceList.get().stream()
                .map(p -> employeePerformanceToEmployeePerformanceDtoConverter.convert(p))
                .collect(Collectors.toList());


        return new ResponseEntity<>(performanceDtoList, HttpStatus.OK);
    }
}
