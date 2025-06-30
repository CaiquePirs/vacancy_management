package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyCreateRequestDTO;
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

@RestController
@RequestMapping("/company/auth")
@AllArgsConstructor
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;
    private final CreateCompanyUseCase createCompanyUseCase;
    private final CompanyMapper companyMapper;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthCompanyRequestDTO companyDTO) {
       try {
           var result = authCompanyUseCase.execute(companyDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(result);

       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
       }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid CompanyCreateRequestDTO companyCreateRequestDTO){
        try {
            var companyCreated = createCompanyUseCase.execute(companyCreateRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toDTO(companyCreated));

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
