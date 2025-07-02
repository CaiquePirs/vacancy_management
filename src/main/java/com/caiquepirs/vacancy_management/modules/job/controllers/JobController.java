package com.caiquepirs.vacancy_management.modules.job.controllers;

import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.useCases.JobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/jobs")
@AllArgsConstructor
public class JobController {

    private final JobUseCase jobUseCase;
    private final JobMapper jobMapper;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@RequestBody @Valid JobCreateRequestDTO jobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id").toString();

        try {
            var jobCreated = jobUseCase.create(UUID.fromString(companyId), jobDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobMapper.toDTO(jobCreated));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Page<JobResponseDTO>> jobList(HttpServletRequest request,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size){

        var companyId  = request.getAttribute("company_id").toString();
        var jobsList = jobUseCase.listJobsByCompany(UUID.fromString(companyId), page, size);
        return ResponseEntity.ok().body(jobsList);
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

    @PutMapping("{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<JobResponseDTO> update(@RequestBody @Valid JobUpdateRequestDTO jobDTO,
                                                 @PathVariable(name = "id" ) UUID jobId,
                                                 HttpServletRequest request){

        var companyId = request.getAttribute("company_id").toString();
        var jobUpdated = jobUseCase.update(UUID.fromString(companyId), jobId , jobDTO);
        return ResponseEntity.ok(jobMapper.toDTO(jobUpdated));
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> toggleJobStatus(@PathVariable(name = "id") UUID jobId, HttpServletRequest request){
        var companyId = request.getAttribute("company_id").toString();

        try {
            jobUseCase.toggleJobStatus(UUID.fromString(companyId), jobId);
            return ResponseEntity.noContent().build();

       } catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}
