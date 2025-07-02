package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.mappers.CompanyMapper;
import com.caiquepirs.vacancy_management.modules.company.useCases.AuthCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company/auth")
@AllArgsConstructor
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;
    private final CreateCompanyUseCase createCompanyUseCase;
    private final CompanyMapper companyMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthCompanyResponseDTO> login(@RequestBody AuthCompanyRequestDTO companyDTO) throws AuthenticationException {
            var result = authCompanyUseCase.execute(companyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyResponseDTO> register(@RequestBody @Valid CompanyCreateRequestDTO companyCreateRequestDTO) {
        var companyCreated = createCompanyUseCase.execute(companyCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toDTO(companyCreated));
    }
}
