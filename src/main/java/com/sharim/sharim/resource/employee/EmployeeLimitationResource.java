package com.sharim.sharim.resource.employee;

import com.sharim.sharim.converters.LimitationDtoToLimitationConverter;
import com.sharim.sharim.converters.LimitationToLimitationDtoConverter;
import com.sharim.sharim.dto.LimitationGroupDto;
import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.services.LimitationService;
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
public class EmployeeLimitationResource{

    @Autowired
    LimitationService limitationService;

    @Autowired
    LimitationToLimitationDtoConverter limitationToLimitationDtoConverter;

    @Autowired
    LimitationDtoToLimitationConverter limitationDtoToLimitationConverter;


    @RequestMapping(value = "limitations", method = RequestMethod.GET)
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


    @RequestMapping("limitations/{limId}")
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> getLimitation(@PathVariable("id") String id,
                                           @PathVariable("limId") int limId) throws Exception {

        Optional<LimitationEntity> limitationEntity = limitationService.findByIdAndEmpId(limId, id);

        if (!limitationEntity.isPresent()) {
            return new ResponseEntity<>("can't find limitation", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(limitationToLimitationDtoConverter.convert(limitationEntity.get()), HttpStatus.OK);

    }


    @RequestMapping(value = "limitations/{limId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('Admin') || hasAuthority(#id)")
    public ResponseEntity<?> deleteLimitation(@PathVariable("id") String id,
                                              @PathVariable("limId") int limId) throws Exception {

        Optional<LimitationEntity> limitationEntity = limitationService.findByIdAndEmpId(limId, id);

        if (!limitationEntity.isPresent()) {
            return new ResponseEntity<>("can't find limitation", HttpStatus.NO_CONTENT);
        }

        limitationService.delete(limitationEntity.get());

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @RequestMapping(value = "limitations", method = RequestMethod.POST)
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
}
