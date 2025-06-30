package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
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

    public CompanyEntity execute(CompanyRequestDTO companyRequestDTO){
        repository.findByUsernameOrEmail(companyRequestDTO.username(), companyRequestDTO.email())
                .ifPresent(company -> {
                    throw new UserFoundException("This company already exist");
                });

        var companyEntity = companyMapper.toEntity(companyRequestDTO);
        companyEntity.setPassword(encoder.encode(companyRequestDTO.password()));
        return repository.save(companyEntity);
    }

}
