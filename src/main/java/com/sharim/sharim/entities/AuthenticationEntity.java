package com.sharim.sharim.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.sharim.sharim.entities.AuthenticationEntity.EmployeeStatus.NotActive;

@Entity
@Table(name="employee")
@Data
public class AuthenticationEntity implements UserDetails {



    public enum EmployeeStatus {
        Manager, Regular, NotActive
    }

    @Id
    String empId;

    String username;

    String password;

    @Column(name = "auth")
    EmployeeStatus status;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != NotActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != NotActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != NotActive;
    }

    @Override
    public boolean isEnabled() {
        return status != NotActive;
    }


}
