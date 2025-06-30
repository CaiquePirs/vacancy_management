package com.caiquepirs.vacancy_management.modules.company.mappers;

import com.caiquepirs.vacancy_management.modules.company.dto.CompanyCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyEntity toEntity(CompanyCreateRequestDTO companyCreateRequestDTO);
    CompanyResponseDTO toDTO(CompanyEntity companyEntity);
}
