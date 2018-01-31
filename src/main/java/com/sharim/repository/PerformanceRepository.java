package com.sharim.repository;

import com.sharim.entities.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<PerformanceEntity, Integer> {
    List<PerformanceEntity> findByPerformanceDateGreaterThanEqualAndPerformanceDateLessThanEqual(Date startDate, Date endDate);
}
