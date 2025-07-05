package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caiquepirs.vacancy_management.exceptions.InvalidCredentialsException;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    private final CandidateRepository repository;
    private final PasswordEncoder encoder;
    private final String secretKey;

    public AuthCandidateUseCase(CandidateRepository repository, PasswordEncoder encoder,
                                @Value("${SECRET_KEY_CANDIDATE}") String secretToken) {
        this.repository = repository;
        this.encoder = encoder;
        this.secretKey = secretToken;
    }

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO candidateRequestDTO) {
        var candidate = repository.findByUsername(candidateRequestDTO.username())
                .orElseThrow(() -> new InvalidCredentialsException("Username/password incorrect"));

        var matches = encoder.matches(candidateRequestDTO.password(), candidate.getPassword());

        if(!matches){
            throw new InvalidCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("jobAPI")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expire_in(expiresIn.toEpochMilli())
                .build();

    }
}
