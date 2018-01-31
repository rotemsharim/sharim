package com.sharim.repository;

import com.sharim.entities.LimitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LimitationRepository extends JpaRepository<LimitationEntity, Integer> {

    List<LimitationEntity> findByLimGroupAndEmpId(int limGroup,String empId);

    List<LimitationEntity> findByEmpId(String empId);

    List<LimitationEntity> findByEmpIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String empId, Date startDate, Date endDate);

    @Query("SELECT max(limGroup) FROM LimitationEntity")
    int findMaxLimGroup();

    LimitationEntity findUniqueByLimIdAndEmpId(int limId, String empId);



}
