package com.caiquepirs.vacancy_management.modules.candidate.mappers;

import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    Candidate toEntity(ProfileCandidateRequestDTO candidateRequestDTO);
    ProfileCandidateResponseDTO toResponseDTO(Candidate candidate);
}
