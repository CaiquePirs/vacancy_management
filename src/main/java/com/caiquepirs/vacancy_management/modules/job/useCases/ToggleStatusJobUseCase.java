package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.useCases.FindProfileCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ToggleStatusJobUseCase {

    private final JobRepository repository;
    private final FindProfileCompanyUseCase findProfileCompanyUseCase;

    public void execute(UUID companyId, UUID jobId) {
        Company company = findProfileCompanyUseCase.execute(companyId);

        Job job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job ID not found"));

        if (job.getStatus().equals(JobStatus.ACTIVE)) job.setStatus(JobStatus.INACTIVE);
        else job.setStatus(JobStatus.ACTIVE);

        repository.save(job);
    }

}
