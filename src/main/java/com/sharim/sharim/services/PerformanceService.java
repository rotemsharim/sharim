package com.sharim.sharim.services;

import com.sharim.sharim.entities.PerformanceEntity;
import com.sharim.sharim.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    PerformanceRepository performanceRepository;

    public PerformanceEntity byId(int id) {
        return performanceRepository.findOne(id);
    }

    public Optional<List<PerformanceEntity>> findByEmpIdAndStartDateRange(String empId, Date startDate, Date endDate) {
        return Optional.ofNullable(performanceRepository.findByPerformanceDateGreaterThanEqualAndPerformanceDateLessThanEqual(startDate,endDate));
    }
}
