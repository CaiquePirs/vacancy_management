package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.dto.JobRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.company.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class JobUseCase {

    private final JobRepository jobRepository;
    private final ProfileCompanyUseCase companyUseCase;
    private final JobMapper jobMapper;

    public JobEntity create(UUID companyID, JobRequestDTO jobDTO){
        var companyId = companyUseCase.getProfile(companyID);

        var job = jobMapper.toEntity(jobDTO);
        job.setCompany(companyId);
        job.setStatus(JobStatus.ACTIVE);

        return jobRepository.save(job);
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

}
