package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import com.caiquepirs.vacancy_management.modules.company.utils.ValidateUpdateCompanyField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateProfileCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final ValidateUpdateCompanyField validate;
    private final FindProfileCompanyUseCase findProfileCompanyUseCase;

    public Company execute(UUID companyId, CompanyUpdateRequestDTO companyDTO){
        Company company = findProfileCompanyUseCase.execute(companyId);
        Company companyUpdated = validate.validate(company, companyDTO);

        companyRepository.findByUsernameOrEmail(companyUpdated.getUsername(), companyUpdated.getEmail())
                .ifPresent(companyFound -> {
                    if (!companyFound.getId().equals(companyUpdated.getId())) {
                        throw new UserFoundException("This username or email is already in use");
                    }
                });
        return companyRepository.save(companyUpdated);

    }

}
