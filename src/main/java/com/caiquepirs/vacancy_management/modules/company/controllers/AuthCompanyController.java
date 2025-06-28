package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.caiquepirs.vacancy_management.modules.company.useCases.AuthCompanyUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> login(@RequestBody AuthCompanyDTO companyDTO) {
       try {
           var result = authCompanyUseCase.execute(companyDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(result);

       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
       }
    }
}
