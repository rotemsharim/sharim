package com.sharim.repository;

import com.sharim.entities.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity,String> {
    AuthenticationEntity findUniqueByUsernameAndPasswordAndStatusNot(String username, String password, AuthenticationEntity.EmployeeStatus status);

    AuthenticationEntity findUniqueByUsername(String username);
}
