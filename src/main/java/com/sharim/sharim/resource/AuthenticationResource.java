package com.sharim.sharim.resource;

import com.sharim.sharim.entities.AuthenticationEntity;
import com.sharim.sharim.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/auth")
public class AuthenticationResource {

//    @Autowired
//    AuthenticationService authenticationService;
//
//    @RequestMapping
//    public ResponseEntity<?> authenticate(String username, String password) {
//
//        AuthenticationEntity authenticationEntity = authenticationService.authenticateUser(username, password);
//
//        if (authenticationEntity == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        return ResponseEntity.ok(authenticationEntity);
//    }

}
