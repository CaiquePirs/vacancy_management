package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.useCases.FindProfileCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteJobUseCase {

    private final JobRepository repository;
    private final FindProfileCompanyUseCase findProfileCompanyUseCase;

    @Transactional
    public void execute(UUID companyID, UUID jobId) {
        Company company = findProfileCompanyUseCase.execute(companyID);

        Job job = company.getVacanciesCreated()
                .stream()
                .filter(j -> j.getId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new JobNotFoundException("Job ID not found"));

        job.getCandidates().forEach(candidate -> candidate.getJobApplications().remove(job));

        job.getCandidates().clear();
        repository.delete(job);
    }




}
