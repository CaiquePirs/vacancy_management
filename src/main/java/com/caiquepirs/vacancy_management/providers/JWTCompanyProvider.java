package com.caiquepirs.vacancy_management.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCompanyProvider {

    private final String secretKey;

    public JWTCompanyProvider(@Value("${SECRET_KEY_COMPANY}") String secretKey){
        this.secretKey = secretKey;
    }

    public DecodedJWT validateToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
