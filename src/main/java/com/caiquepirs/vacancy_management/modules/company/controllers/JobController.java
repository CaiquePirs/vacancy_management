package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.JobRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.company.useCases.JobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@AllArgsConstructor
public class JobController {

    private final JobUseCase jobUseCase;
    private final JobMapper jobMapper;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@RequestBody @Valid JobRequestDTO jobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id").toString();

        try {
            var jobCreated = jobUseCase.create(UUID.fromString(companyId), jobDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobMapper.toDTO(jobCreated));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID jobId, HttpServletRequest request){
        var companyId = request.getAttribute("company_id").toString();

        try {
            jobUseCase.delete(UUID.fromString(companyId), jobId);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
