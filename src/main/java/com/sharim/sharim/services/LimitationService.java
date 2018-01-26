package com.sharim.sharim.services;

import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.repository.LimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LimitationService {

    @Autowired
    LimitationRepository limitationRepository;

    public Optional<List<LimitationEntity>> findByLimGroup(int limGroup) {
        return Optional.ofNullable(limitationRepository.findByLimGroup(limGroup));
    }

    public Optional<List<LimitationEntity>> findByEmpId(String empId) {
        return Optional.ofNullable(limitationRepository.findByEmpId(empId));

    }

    public Optional<List<LimitationEntity>> findByEmpIdBetweenStartDateAndEndDate(String empId, Date startDate, Date endDate) {
        return Optional.ofNullable(limitationRepository.findByEmpIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(empId, startDate, endDate));
    }

}
