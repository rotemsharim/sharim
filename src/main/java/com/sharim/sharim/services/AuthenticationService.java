package com.sharim.sharim.services;

import com.sharim.sharim.entities.AuthenticationEntity;
import com.sharim.sharim.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthenticationService {

    String secret;
    int expirationTime;

    @Autowired
    AuthenticationRepository authenticationRepository;

    public AuthenticationService(String secret, int expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    public AuthenticationEntity authenticateUser(String username, String password) {

        AuthenticationEntity authenticationEntity = authenticationRepository
                .findUniqueByUsernameAndPasswordAndStatusNot(username, password, AuthenticationEntity.EmployeeStatus.NotActive);

        if (authenticationEntity == null) {
            return null;
        }

        return authenticationEntity;
    }

    private Date getExpirationDate() {
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expirationTime);
        return new Date(expireInMilis + now.getTime());
    }

    protected JwtTokenDto getToken(int userId, Set<RoleEntity> roles) {
        try {

            Set<String> RolesName = new HashSet<String>();
            for (RoleEntity role : roles) {
                RolesName.add(role.getName());
            }

            String encodedSignature = generateEncodedSecret(secretKey);

            String jwt = Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setSubject(Integer.toString(userId))
                    .claim("roles", RolesName)
                    .setIssuedAt(new Date())
                    .setExpiration(getExpirationTime())
                    .signWith(SignatureAlgorithm.HS512, encodedSignature)
                    .compact();

            JwtTokenDto jwtToken = new JwtTokenDto();
            jwtToken.setToken(jwt);
            jwtToken.setExpiresIn(getExpirationTime());
            jwtToken.setTokenType(TOKEN_TYPE);
            return jwtToken;
        } catch (Exception e) {
            logger.error("Error Generate Token", e);
            return null;
        }

    }
}
