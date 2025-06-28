package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyUseCase {

    private final CompanyRepository repository;
    private final PasswordEncoder encoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        repository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .orElseThrow(() -> new UserFoundException("This company already exist"));

        companyEntity.setPassword(encoder.encode(companyEntity.getPassword()));
        return repository.save(companyEntity);
    }

}
