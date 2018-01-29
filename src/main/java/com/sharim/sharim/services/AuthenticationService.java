package com.sharim.sharim.services;

import com.sharim.sharim.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

//    String secret;
//    int expirationTime;

    @Autowired
    AuthenticationRepository authenticationRepository;

//    public AuthenticationService(String secret, int expirationTime) {
//        this.secret = secret;
//        this.expirationTime = expirationTime;
//    }
//
//    public AuthenticationEntity authenticateUser(String username, String password) {
//
//        AuthenticationEntity authenticationEntity = authenticationRepository
//                .findUniqueByUsernameAndPasswordAndStatusNot(username, password, AuthenticationEntity.EmployeeStatus.NotActive);
//
//        if (authenticationEntity == null) {
//            return null;
//        }
//
//        return authenticationEntity;
//    }
//
//    private Date getExpirationDate() {
//        Date now = new Date();
//        Long expireInMilis = TimeUnit.HOURS.toMillis(expirationTime);
//
//
//
//        return new Date(expireInMilis + now.getTime());
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationRepository.findUniqueByUsername(username);
    }
}
