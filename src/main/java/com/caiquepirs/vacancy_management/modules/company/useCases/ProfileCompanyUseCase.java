package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CompanyEntity getProfile(UUID id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Company not found"));
    }
}
