package com.sharim.sharim.services;

import com.sharim.sharim.entities.LimitationEntity;
import com.sharim.sharim.repository.LimitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LimitationService {

    @Autowired
    LimitationRepository limitationRepository;

    public Optional<List<LimitationEntity>> findByLimGroup(int limGroup, String empId) {
        return Optional.ofNullable(limitationRepository.findByLimGroupAndEmpId(limGroup,empId));
    }

    public Optional<List<LimitationEntity>> findByEmpId(String empId) {
        return Optional.ofNullable(limitationRepository.findByEmpId(empId));

    }

    public Optional<LimitationEntity> findByIdAndEmpId(int limId,String empId) {
        return Optional.ofNullable(limitationRepository.findUniqueByLimIdAndEmpId(limId,empId));

    }

    public Optional<List<LimitationEntity>> findByEmpIdBetweenStartDateAndEndDate(String empId, Date startDate, Date endDate) {
        return Optional.ofNullable(limitationRepository.findByEmpIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(empId, startDate, endDate));
    }

    public boolean isLimitationForEmployeeBetweenDates(String empId, Date fromDate, Date toDate) {
        return findByEmpIdBetweenStartDateAndEndDate(empId,fromDate,toDate).isPresent();
    }

    public void delete(List<LimitationEntity> entityList) throws Exception{
        limitationRepository.delete(entityList);
    }



    public void save(List<LimitationEntity> entityList) {

        //filter the list - find all perofrmances between min and max ->
        // check if allowed to add limitation (no performance in this date) - if there is -> send for confirmation

        int maxLimGroup = limitationRepository.findMaxLimGroup() + 1;
        entityList.forEach(e -> {
            e.setLimGroup(maxLimGroup);

        });

        limitationRepository.save(entityList);

    }

}
