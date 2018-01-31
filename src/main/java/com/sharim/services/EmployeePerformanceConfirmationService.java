package com.sharim.services;

import com.sharim.entities.EmployeeConfirmationEntity;
import com.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.exceptions.NotAllowedToDenyException;
import com.sharim.exceptions.NotFoundException;
import com.sharim.helpers.DateHelper;
import com.sharim.repository.EmployeeConfirmationRepository;
import com.sharim.repository.PerformanceEmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class EmployeePerformanceConfirmationService {


    @Autowired
    PerformanceEmployeeRepository performanceEmployeeRepository;

    @Autowired
    LimitationService limitationService;

    @Autowired
    EmployeeConfirmationRepository employeeConfirmationRepository;


    public void updateConfirmation(String empId, int perId, boolean confirmation) throws NotFoundException, NotAllowedToDenyException {
        //find the performance
        //check if allowed to deng -> check whether there's a limitation in this time -> if yes -> throw exception
        PerformanceEmployeeEntity performanceEmployeeEntity = performanceEmployeeRepository.findTopById_EmpIdAndId_Performance_PerId(empId, perId);

        if (performanceEmployeeEntity == null) {
            log.debug("couldn't find performance by perId {} and empId {}", perId, empId);
            throw new NotFoundException();
        }

        if (!confirmation && !isAllowedToDeny(empId,
                performanceEmployeeEntity.getId().getPerformance().getPerformanceDate(),
                performanceEmployeeEntity.isConfirm())) {
            log.info("per id {} emp id {} not allowed to deny", perId, empId);
            throw new NotAllowedToDenyException();
        }

        saveConfirmation(confirmation, performanceEmployeeEntity);

    }

    private void saveConfirmation(boolean confirmation, PerformanceEmployeeEntity performanceEmployeeEntity) {
        performanceEmployeeEntity.setConfirm(confirmation);
        performanceEmployeeEntity.setConfirmDate(new Date());

        performanceEmployeeRepository.save(performanceEmployeeEntity);

        EmployeeConfirmationEntity confirmationEntity = employeeConfirmationRepository.findTopByEmpIdAndPerIdOrderByConfirmIdDesc(performanceEmployeeEntity.getId().getEmpId(), performanceEmployeeEntity.getId().getPerformance().getPerId());
        if (confirmationEntity != null) {
            confirmationEntity.setConfirmDate(new Date());
            confirmationEntity.setConfirmation(confirmation);
            employeeConfirmationRepository.save(confirmationEntity);
        }

    }

    private boolean isAllowedToDeny(String empId, Date date, boolean currentConfirmStatus) {

        if (currentConfirmStatus == true) {
            log.debug("empId {} not allowed to deny because status already confirmed",empId);
            return false;
        }

        return limitationService.isLimitationForEmployeeBetweenDates(empId,
                DateHelper.removeTimeFromDate(date),
                DateHelper.addTimeToDate(date, 23, 59, 59, 0));
    }


}
