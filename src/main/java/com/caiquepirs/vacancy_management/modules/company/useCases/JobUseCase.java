package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JobUseCase {

    private final JobRepository repository;

    public JobEntity execute(JobEntity jobEntity){
        return repository.save(jobEntity);
    }
}
