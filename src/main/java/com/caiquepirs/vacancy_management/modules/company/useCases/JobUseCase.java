package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.dto.JobRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.company.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import com.caiquepirs.vacancy_management.modules.company.util.ValidateUpdateJobField;
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

    public JobEntity create(UUID companyID, JobRequestDTO jobDTO){
        var companyId = companyUseCase.getProfile(companyID);

        var job = jobMapper.toEntity(jobDTO);
        job.setCompany(companyId);
        job.setStatus(JobStatus.ACTIVE);

        return jobRepository.save(job);
    }

    public Page<JobResponseDTO> listJobs(UUID companyId, int page, int size){
        return jobRepository.findByCompanyId(companyId, PageRequest.of(page, size))
                .map(jobMapper::toDTO);
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
