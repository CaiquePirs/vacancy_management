package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caiquepirs.vacancy_management.exceptions.InvalidCredentialsException;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final String secretKey;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder,
                              @Value("${SECRET_KEY_COMPANY}") String secretToken) {

        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.secretKey = secretToken;
    }

        public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompanyRequestDTO) {
        Company company = companyRepository.findByUsername(authCompanyRequestDTO.username())
                .orElseThrow(() -> new InvalidCredentialsException("Username/password incorrect"));

        boolean matches = passwordEncoder.matches(authCompanyRequestDTO.password(), company.getPassword());

        if(!matches){
            throw new InvalidCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expireIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("jobAPI")
                .withExpiresAt(expireIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expire_at(expireIn.toEpochMilli())
                .build();
    }

}
