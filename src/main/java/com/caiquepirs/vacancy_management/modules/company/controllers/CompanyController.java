package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.mappers.CompanyMapper;
import com.caiquepirs.vacancy_management.modules.company.useCases.ProfileCompanyUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final ProfileCompanyUseCase companyUseCase;
    private final CompanyMapper companyMapper;

    @GetMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();

        try {
            var companyProfile = companyUseCase.getProfile(UUID.fromString(companyId));
            return ResponseEntity.ok(companyMapper.toDTO(companyProfile));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
