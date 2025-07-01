package com.caiquepirs.vacancy_management.modules.company.mappers;

import com.caiquepirs.vacancy_management.modules.company.dto.JobRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobEntity toEntity(JobRequestDTO jobRequestDTO);
    JobResponseDTO toDTO(JobEntity jobEntity);

}
