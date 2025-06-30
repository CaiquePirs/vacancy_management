package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.CompanyUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.mappers.CompanyMapper;
import com.caiquepirs.vacancy_management.modules.company.useCases.ProfileCompanyUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> deleteProfile(HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();

        try {
            companyUseCase.deleteProfile(UUID.fromString(companyId));
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping
    public ResponseEntity<Object> updateProfile(@RequestBody @Valid CompanyUpdateRequestDTO companyDTO,
                                                HttpServletRequest request){
        var companyId = request.getAttribute("company_id").toString();

        try {
            var companyUpdated = companyUseCase.updateProfile(UUID.fromString(companyId), companyDTO);
            return ResponseEntity.ok(companyMapper.toDTO(companyUpdated));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
