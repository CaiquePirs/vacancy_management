package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.mappers.CompanyMapper;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCompanyUseCase {

    private final CompanyRepository repository;
    private final PasswordEncoder encoder;
    private final CompanyMapper companyMapper;

    public Company execute(CompanyCreateRequestDTO companyCreateRequestDTO){
        repository.findByUsernameOrEmail(companyCreateRequestDTO.username(), companyCreateRequestDTO.email())
                .ifPresent(company -> {
                    throw new UserFoundException("This company already exist");
                });

        Company company = companyMapper.toEntity(companyCreateRequestDTO);
        company.setPassword(encoder.encode(companyCreateRequestDTO.password()));
        return repository.save(company);
    }

}
