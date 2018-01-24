package com.sharim.sharim.services;

import com.sharim.sharim.entities.Limitation;
import com.sharim.sharim.repository.LimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LimitationService {

    @Autowired
    LimitationRepository limitationRepository;

    List<Limitation> findByLimGroup(int limGroup) {
        return limitationRepository.findByLimGroup(limGroup);
    }

    List<Limitation> findByEmpId(String empId) {
        return findByEmpId(empId);

    }

    List<Limitation> findByEmpIdAndStartDateAndEndDate(String empId, Date startDate, Date endDate) {
        return findByEmpIdAndStartDateAndEndDate(empId, startDate, endDate);
    }
}
