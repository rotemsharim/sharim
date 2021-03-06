package com.sharim.sharim.repository;

import com.sharim.sharim.entities.LimitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LimitationRepository extends JpaRepository<LimitationEntity, Integer> {

    List<LimitationEntity> findByLimGroup(int limGroup);

    List<LimitationEntity> findByEmpId(String empId);

    List<LimitationEntity> findByEmpIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String empId, Date startDate, Date endDate);


}
