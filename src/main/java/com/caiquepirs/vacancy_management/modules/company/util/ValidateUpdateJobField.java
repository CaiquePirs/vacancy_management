package com.caiquepirs.vacancy_management.modules.company.util;

import com.caiquepirs.vacancy_management.modules.company.dto.JobUpdateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidateUpdateJobField {

    public JobEntity validate(JobEntity jobEntity, JobUpdateRequestDTO jobDTO){

        if (jobDTO.description() != null) {
            jobEntity.setDescription(jobDTO.description());
        }

        if (jobDTO.level() != null) {
            jobEntity.setLevel(jobDTO.level());
        }

        if (jobDTO.benefits() != null) {
            jobEntity.setBenefits(jobDTO.benefits());
        }

        if (jobDTO.salary() != null) {
            jobEntity.setSalary(jobDTO.salary());
        }

        return jobEntity;
    }
}
