package com.sharim.repository;

import com.sharim.entities.PerformanceEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PerformanceEmployeeRepository extends JpaRepository<PerformanceEmployeeEntity, PerformanceEmployeeEntity.IdKey> {
    List<PerformanceEmployeeEntity> findById_EmpIdAndId_Performance_PerformanceDateGreaterThanEqualAndId_Performance_PerformanceDateLessThanEqual(String empId,Date fromDate,Date toDate);

    PerformanceEmployeeEntity findTopById_EmpIdAndId_Performance_PerId(String empId, int perId);
}
