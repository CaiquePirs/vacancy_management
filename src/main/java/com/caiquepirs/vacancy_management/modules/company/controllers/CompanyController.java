package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.docs.CompanyControllerDoc;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
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
public class CompanyController implements CompanyControllerDoc {

    private final ProfileCompanyUseCase companyUseCase;
    private final CompanyMapper companyMapper;

    @GetMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<CompanyResponseDTO> getProfile(HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();
        var companyProfile = companyUseCase.getProfile(UUID.fromString(companyId));
        return ResponseEntity.ok(companyMapper.toDTO(companyProfile));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Void> deleteProfile(HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();
        companyUseCase.deleteProfile(UUID.fromString(companyId));
        return ResponseEntity.noContent().build();
    }


    @PutMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<CompanyResponseDTO> updateProfile(@RequestBody @Valid CompanyUpdateRequestDTO companyDTO,
                                                HttpServletRequest request) {
        var companyId = request.getAttribute("company_id").toString();
        var companyUpdated = companyUseCase.updateProfile(UUID.fromString(companyId), companyDTO);
        return ResponseEntity.ok(companyMapper.toDTO(companyUpdated));
    }

}
