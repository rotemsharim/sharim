package com.sharim.sharim.services;

import com.sharim.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.repository.PerformanceEmployeeRepository;
import com.sharim.sharim.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceService {

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    PerformanceEmployeeRepository performanceEmployeeRepository;


    public PerformanceEntity byId(int id) {
        return performanceRepository.findOne(id);
    }

    public Optional<List<PerformanceEmployeeEntity>> findByEmpIdAndStartDateRange(String empId, Date fromDate, Date toDate) {
        List<PerformanceEmployeeEntity> performanceEmployeeEntityList = performanceEmployeeRepository.findById_EmpIdAndId_Performance_PerformanceDateGreaterThanEqualAndId_Performance_PerformanceDateLessThanEqual(empId,fromDate,toDate);

        return Optional.ofNullable(performanceEmployeeEntityList.stream()
                .filter(e -> e.isActive() && e.getId().getPerformance().getStatus()!= PerformanceEntity.PerformanceStatus.Cancelled)
                .collect(Collectors.toList()));
    }


}
