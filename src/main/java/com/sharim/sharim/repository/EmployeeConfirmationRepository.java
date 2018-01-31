package com.sharim.sharim.repository;

import com.sharim.sharim.entities.EmployeeConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeConfirmationRepository extends JpaRepository<EmployeeConfirmationEntity,Integer> {

    EmployeeConfirmationEntity findTopByEmpIdAndPerIdOrderByConfirmIdDesc(String empId, int perId);
}
