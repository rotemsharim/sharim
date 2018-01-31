package com.sharim.sharim.services;

import com.sharim.sharim.entities.EmployeeConfirmationEntity;
import com.sharim.sharim.entities.PerformanceEmployeeEntity;
import com.sharim.sharim.exceptions.NotAllowedToDenyException;
import com.sharim.sharim.exceptions.NotFoundException;
import com.sharim.sharim.helpers.DateHelper;
import com.sharim.sharim.repository.EmployeeConfirmationRepository;
import com.sharim.sharim.repository.PerformanceEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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
            throw new NotFoundException();
        }

        if (!confirmation && !isAllowedToDeny(empId,performanceEmployeeEntity.getId().getPerformance().getPerformanceDate())) {
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

    private boolean isAllowedToDeny(String empId, Date date) {

        return !limitationService.isLimitationForEmployeeBetweenDates(empId,
                DateHelper.removeTimeFromDate(date),
                DateHelper.addTimeToDate(date,23,59,59,0));
    }




}
