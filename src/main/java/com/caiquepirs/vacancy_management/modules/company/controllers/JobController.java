package com.caiquepirs.vacancy_management.modules.company.controllers;

import com.caiquepirs.vacancy_management.modules.company.dto.CreateJobDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.useCases.JobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@AllArgsConstructor
public class JobController {

    private final JobUseCase jobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@RequestBody @Valid CreateJobDTO jobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");

       var jobEntity = JobEntity.builder()
                .idCompany(UUID.fromString(companyId.toString()))
                .benefits(jobDTO.benefits())
                .description(jobDTO.description())
                .level(jobDTO.level())
                .build();

        return jobUseCase.execute(jobEntity);
    }

}
