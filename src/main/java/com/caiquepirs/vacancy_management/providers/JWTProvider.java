package com.caiquepirs.vacancy_management.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    @Value("${SECRET_TOKEN}")
    private String SECRET_TOKEN;

    public String validateToken(String token){
        token = token.replace("Bearer", "");

        Algorithm algorithm = Algorithm.HMAC256(SECRET_TOKEN);

        try{
          var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;

        } catch (JWTVerificationException e){
            return "";
        }

    }
}
