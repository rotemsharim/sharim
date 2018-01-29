package com.sharim.sharim.services;

import com.sharim.sharim.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  extends AbstractUserDetailsAuthenticationProvider implements UserDetailsService {

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

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!(userDetails.getUsername().equals(authentication.getPrincipal()) && userDetails.getPassword().equals(authentication.getCredentials()))) {
            throw new BadCredentialsException("invalid username password");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return authenticationRepository.findUniqueByUsername(username);
    }
}
