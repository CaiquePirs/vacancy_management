package com.caiquepirs.vacancy_management.modules.job.mappers;

import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.JobEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobEntity toEntity(JobCreateRequestDTO jobCreateRequestDTO);
    JobResponseDTO toDTO(JobEntity jobEntity);

}
