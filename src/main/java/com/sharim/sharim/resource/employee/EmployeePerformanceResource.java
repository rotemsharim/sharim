package com.sharim.sharim.resource.employee;

import com.sharim.sharim.converters.EmployeePerformanceToEmployeePerformanceDtoConverter;
import com.sharim.sharim.dto.EmployeePerformanceDto;
import com.sharim.sharim.dto.EmployeePerformanceUpdateDto;
import com.sharim.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.sharim.exceptions.NotAllowedToDenyException;
import com.sharim.sharim.exceptions.NotFoundException;
import com.sharim.sharim.services.EmployeePerformanceConfirmationService;
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
@RequestMapping("employees/{id}")
public class EmployeePerformanceResource {


    @Autowired
    PerformanceService performanceService;

    @Autowired
    EmployeePerformanceToEmployeePerformanceDtoConverter employeePerformanceToEmployeePerformanceDtoConverter;

    @Autowired
    EmployeePerformanceConfirmationService employeePerformanceConfirmationService;

    private static final Logger logger = LogManager.getLogger(EmployeePerformanceResource.class);


    @RequestMapping("performances")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> getPerformance(@PathVariable("id") String id,
                                            @RequestParam(required = true) Long from,
                                            @RequestParam(required = true) Long to) throws Exception {


        Optional<List<PerformanceEmployeeEntity>> performanceList = performanceService.findByEmpIdAndStartDateRange(id, new Date(from), new Date(to));

        if (!performanceList.isPresent() || performanceList.get().size() == 0) {
            logger.info("no performances for emp {} between {} and {}", id, from, to);
            return new ResponseEntity<>("no limitations for employee between these dates", HttpStatus.NO_CONTENT);
        }

        List<EmployeePerformanceDto> performanceDtoList = performanceList.get().stream()
                .map(p -> employeePerformanceToEmployeePerformanceDtoConverter.convert(p))
                .collect(Collectors.toList());

        logger.info("found {} performances for emp {} between {} and {}", performanceDtoList.size(), id, from, to);

        return new ResponseEntity<>(performanceDtoList, HttpStatus.OK);
    }

    @RequestMapping("performances/{per_id}")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> getPerformance(@PathVariable("id") String id,
                                            @PathVariable("per_id") int perId) throws NotFoundException {


        Optional<PerformanceEmployeeEntity> performance = performanceService.byIdAndEmpId(perId, id);

        if (!performance.isPresent()) {
            logger.info("no performance with id {} for emp {}", perId, id);
            throw new NotFoundException();
        }

        logger.info("found performance with id {} for emp {}", perId, id);

        return new ResponseEntity<>(employeePerformanceToEmployeePerformanceDtoConverter.convert(performance.get()), HttpStatus.OK);
    }

    @RequestMapping(value = "performances/{per_id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> updateEmployeePerformanceDetails(@PathVariable("id") String id,
                                                              @PathVariable("per_id") int perId,
                                                              @RequestBody EmployeePerformanceUpdateDto employeePerformanceUpdate) throws NotFoundException, NotAllowedToDenyException {


        Optional<PerformanceEmployeeEntity> performance = performanceService.byIdAndEmpId(perId, id);

        if (!performance.isPresent()) {
            logger.info("no performance with id {} for emp {}", perId, id);
            throw new NotFoundException();
        }

        if (employeePerformanceUpdate.getConfirm() != null) {
            employeePerformanceConfirmationService.updateConfirmation(id,
                    perId,
                    employeePerformanceUpdate.getConfirm());

        }

        logger.info("found performance with id {} for emp {}", perId, id);

        return new ResponseEntity<>(employeePerformanceToEmployeePerformanceDtoConverter.convert(performance.get()), HttpStatus.OK);
    }
}
