package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.useCases.FindProfileCompanyUseCase;
import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateJobUseCase {

    private final JobRepository repository;
    private final FindProfileCompanyUseCase findProfileCompanyUseCase;
    private final JobMapper mapper;

    public Job execute(UUID companyID, JobCreateRequestDTO jobDTO){
        Company company = findProfileCompanyUseCase.execute(companyID);

        Job job = mapper.toEntity(jobDTO);
        job.setCompany(company);
        job.setStatus(JobStatus.ACTIVE);

        return repository.save(job);
    }


}
