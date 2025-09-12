package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindProfileCompanyUseCase {

    private final CompanyRepository companyRepository;

    public Company execute(UUID id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Company ID not found"));
    }
}
