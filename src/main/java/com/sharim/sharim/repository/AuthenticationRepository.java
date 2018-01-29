package com.sharim.sharim.repository;

import com.sharim.sharim.entities.AuthenticationEntity;
import com.sharim.sharim.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity,String> {
    AuthenticationEntity findUniqueByUsernameAndPasswordAndStatusNot(String username, String password, AuthenticationEntity.EmployeeStatus status);
}
