package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindAllJobsByCompanyUseCase {

    private final JobRepository repository;
    private final JobMapper mapper;

    public Page<JobResponseDTO> execute(UUID companyId, int page, int size){
        return repository.findByCompanyId(companyId, PageRequest.of(page, size))
                .map(mapper::toDTO);
    }

}
