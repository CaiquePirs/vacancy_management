package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.useCases.ProfileCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.job.specifications.JobSpecification;
import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobFilterDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.job.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import com.caiquepirs.vacancy_management.modules.job.utils.ValidateUpdateJobField;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class JobUseCase {

    private final JobRepository jobRepository;
    private final ProfileCompanyUseCase companyUseCase;
    private final JobMapper jobMapper;
    private final ValidateUpdateJobField validateJob;

    public JobEntity create(UUID companyID, JobCreateRequestDTO jobDTO){
        var companyId = companyUseCase.getProfile(companyID);

        var job = jobMapper.toEntity(jobDTO);
        job.setCompany(companyId);
        job.setStatus(JobStatus.ACTIVE);

        return jobRepository.save(job);
    }

    public Page<JobResponseDTO> listJobsByCompany(UUID companyId, int page, int size){
        return jobRepository.findByCompanyId(companyId, PageRequest.of(page, size))
                .map(jobMapper::toDTO);
    }

    public Page<JobResponseDTO> listJobsByFilter(JobFilterDTO dto, int page, int size) {
        var listJobs = jobRepository.findAll(JobSpecification.filterBy(dto), PageRequest.of(page, size));
        return listJobs.map(jobMapper::toDTO);
    }

    public JobEntity update(UUID companyId, UUID jobId, JobUpdateRequestDTO jobDTO){
        var company = companyUseCase.getProfile(companyId);

        JobEntity job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        var jobUpdated = validateJob.validate(job, jobDTO);
        return jobRepository.save(jobUpdated);
    }

    @Transactional
    public void delete(UUID companyID, UUID jobId) {
        var company = companyUseCase.getProfile(companyID);

        JobEntity job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        job.getCandidates().clear();
        jobRepository.delete(job);
    }

    public void toggleJobStatus(UUID companyId, UUID jobId) {
        var company = companyUseCase.getProfile(companyId);

        JobEntity job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        if (job.getStatus().equals(JobStatus.ACTIVE)) {
            job.setStatus(JobStatus.INACTIVE);
        } else {
            job.setStatus(JobStatus.ACTIVE);
        }

        jobRepository.save(job);
    }

}
