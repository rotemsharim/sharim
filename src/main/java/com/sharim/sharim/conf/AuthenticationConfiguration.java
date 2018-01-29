package com.sharim.sharim.conf;

import com.sharim.sharim.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfiguration {

    final String secret = "Rotem";
    final int expirationTime = 100;

    @Bean
    AuthenticationService authenticationService() {
        return new AuthenticationService(secret,expirationTime);
    }




}
