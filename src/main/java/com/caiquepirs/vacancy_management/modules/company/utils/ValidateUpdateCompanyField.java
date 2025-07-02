package com.caiquepirs.vacancy_management.modules.company.utils;

import com.caiquepirs.vacancy_management.modules.company.dto.CompanyUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateUpdateCompanyField {

    private final PasswordEncoder encoder;

    public CompanyEntity validate(CompanyEntity company, CompanyUpdateRequestDTO companyDTO){

        if (companyDTO.name() != null) {
            company.setName(companyDTO.name());
        }

        if(companyDTO.username() != null){
            company.setUsername(companyDTO.username());
        }

        if(companyDTO.email() != null){
            company.setEmail(companyDTO.email());
        }

        if(companyDTO.description() != null){
            company.setDescription(companyDTO.description());
        }

        if(companyDTO.website() != null){
            company.setWebsite(companyDTO.website());
        }

        if(companyDTO.password() != null){
            company.setPassword(encoder.encode(companyDTO.password()));
        }

        return company;

    }
}
