package com.caiquepirs.vacancy_management.modules.candidate.util;

import com.caiquepirs.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ValidateCandidateField {

    @Autowired
    private PasswordEncoder encoder;

    public CandidateEntity validate(CandidateEntity candidate, ProfileUpdateCandidateRequestDTO profileDTO){

        if (profileDTO.name() != null) {
            candidate.setName(profileDTO.name());
        }

        if (profileDTO.username() != null) {
            candidate.setUsername(profileDTO.username());
        }

        if(profileDTO.email() != null){
            candidate.setEmail(profileDTO.email());
        }

        if(profileDTO.password() != null){
            var passwordEncoder = encoder.encode(profileDTO.password());
            candidate.setPassword(passwordEncoder);
        }

        if(profileDTO.description() != null) {
            candidate.setDescription(profileDTO.description());
        }

        if(profileDTO.curriculum() != null){
            candidate.setCurriculum(profileDTO.curriculum());
        }

        return candidate;
    }

}
