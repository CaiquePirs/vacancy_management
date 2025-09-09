package com.caiquepirs.vacancy_management.modules.job.mappers;

import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toEntity(JobCreateRequestDTO jobCreateRequestDTO);
    JobResponseDTO toDTO(Job job);

}
