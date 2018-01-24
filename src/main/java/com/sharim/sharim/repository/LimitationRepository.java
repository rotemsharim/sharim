package com.sharim.sharim.repository;

import com.sharim.sharim.entities.Employee;
import com.sharim.sharim.entities.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LimitationRepository extends JpaRepository<Limitation, Integer> {

    List<Limitation> findByLimGroup(int limGroup);

    List<Limitation> findByEmpId(String empId);

    List<Limitation> findByEmpIdAndStartDateAndEndDate(String empId, Date startDate, Date endDate);


}
