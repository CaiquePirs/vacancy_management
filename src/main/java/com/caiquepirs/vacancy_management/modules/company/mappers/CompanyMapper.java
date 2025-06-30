package com.caiquepirs.vacancy_management.modules.company.mappers;

import com.caiquepirs.vacancy_management.modules.company.dto.CompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyEntity toEntity(CompanyRequestDTO companyRequestDTO);
    CompanyResponseDTO toDTO(CompanyEntity companyEntity);
}
