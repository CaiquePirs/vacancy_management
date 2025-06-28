package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import com.caiquepirs.vacancy_management.modules.company.useCases.CompanyUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyUseCase useCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CompanyEntity companyEntity){
        try {
            var result = useCase.execute(companyEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
