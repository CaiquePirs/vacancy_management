package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final String secretToken;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder,
                              @Value("${SECRET_TOKEN}") String secretToken) {

        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.secretToken = secretToken;
    }

    public AuthCandidateResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.username())
                .orElseThrow(() -> new UserFoundException("Username/password incorrect"));

        boolean matches = passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

        if(!matches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretToken);
        var token = JWT.create().withIssuer("jobAPI")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .build();
    }

}
