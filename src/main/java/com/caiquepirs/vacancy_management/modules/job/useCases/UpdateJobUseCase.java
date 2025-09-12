package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.useCases.FindProfileCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.job.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import com.caiquepirs.vacancy_management.modules.job.utils.ValidateUpdateJobField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateJobUseCase {

    private final JobRepository jobRepository;
    private final ValidateUpdateJobField validateJob;
    private final FindProfileCompanyUseCase findProfileCompanyUseCase;

    public Job execute(UUID companyId, UUID jobId, JobUpdateRequestDTO jobDTO){
        Company company = findProfileCompanyUseCase.execute(companyId);

        Job job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job ID not found"));

        Job jobUpdated = validateJob.validate(job, jobDTO);
        return jobRepository.save(jobUpdated);
    }


}
