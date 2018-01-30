package com.sharim.sharim.resource;

import com.sharim.sharim.converters.EmployeeToEmployeeDtoConverter;
import com.sharim.sharim.converters.LimitationDtoToLimitationConverter;
import com.sharim.sharim.converters.LimitationToLimitationDtoConverter;
import com.sharim.sharim.converters.PerformanceToPerformanceDtoConverter;
import com.sharim.sharim.dto.LimitationDto;
import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.EmployeeEntity;
import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.services.EmployeeService;
import com.sharim.sharim.services.LimitationService;
import com.sharim.sharim.services.PerformanceService;
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
    LimitationService limitationService;

    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeeToEmployeeDtoConverter employeeToEmployeeDtoConverter;

    @Autowired
    LimitationToLimitationDtoConverter limitationToLimitationDtoConverter;

    @Autowired
    LimitationDtoToLimitationConverter limitationDtoToLimitationConverter;

    @Autowired
    PerformanceToPerformanceDtoConverter performanceToPerformanceDtoConverter;

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
    public ResponseEntity<?> findEmployee(@PathVariable("id") String id) throws Exception {
        Optional<EmployeeEntity> employee = employeeService.findOne(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>("employee doesn't exist", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeToEmployeeDtoConverter.convert(employee.get()), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}/limitations", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
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


    @RequestMapping("/{id}/limitations/{limId}")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> getLimitation(@PathVariable("id") String id,
                                            @PathVariable("limId") int limId
                                            ) throws Exception {

        Optional<LimitationEntity> limitationEntity = limitationService.findById(limId);

        if (!limitationEntity.isPresent()) {
            return new ResponseEntity<>("can't find limitation", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(limitationToLimitationDtoConverter.convert(limitationEntity.get()),HttpStatus.OK);

    }


    @RequestMapping(value = "/{id}/limitations/{limId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> deleteLimitation(@PathVariable("id") String id,
                                            @PathVariable("limId") int limId
    ) throws Exception {

        Optional<LimitationEntity> limitationEntity = limitationService.findById(limId);

        if (!limitationEntity.isPresent()) {
            return new ResponseEntity<>("can't find limitation", HttpStatus.NO_CONTENT);
        }

        limitationService.delete(limitationEntity.get());

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @RequestMapping(value = "/{id}/limitations", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> addLimitation(@PathVariable("id") String id, @RequestBody LimitationGroupDto limitationGroupDto) throws Exception {

        List<LimitationEntity> limitationEntityList = limitationGroupDto.getLimitationList().stream()
                .map(limitationDto -> limitationDtoToLimitationConverter.convert(limitationDto))
                .collect(Collectors.toList());

        limitationEntityList.forEach(l -> {
            l.setEmpId(id);
            l.setInsertDate(new Date());
        });

        limitationService.save(limitationEntityList);

        return new ResponseEntity<>(HttpStatus.OK);

    }



    @RequestMapping("/{id}/performances")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
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
