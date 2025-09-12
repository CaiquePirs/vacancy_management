package com.caiquepirs.vacancy_management.modules.job.validator;

import com.caiquepirs.vacancy_management.modules.job.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import org.springframework.stereotype.Component;

@Component
public class ValidateUpdateJobField {

    public Job validate(Job job, JobUpdateRequestDTO jobDTO){
        if (jobDTO.description() != null) job.setDescription(jobDTO.description());
        if (jobDTO.level() != null) job.setLevel(jobDTO.level());
        if (jobDTO.benefits() != null)job.setBenefits(jobDTO.benefits());
        if (jobDTO.salary() != null) job.setSalary(jobDTO.salary());

        return job;
    }
}
