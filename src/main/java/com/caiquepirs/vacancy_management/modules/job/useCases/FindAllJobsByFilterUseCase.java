package com.caiquepirs.vacancy_management.modules.job.useCases;

import com.caiquepirs.vacancy_management.modules.job.dto.JobFilterDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.mappers.JobMapper;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import com.caiquepirs.vacancy_management.modules.job.specifications.JobSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllJobsByFilterUseCase {

    private final JobRepository repository;
    private final JobMapper mapper;

    public Page<JobResponseDTO> execute(JobFilterDTO dto, int page, int size) {
        Page<Job> listJobs = repository.findAll(JobSpecification.filterBy(dto), PageRequest.of(page, size));
        return listJobs.map(mapper::toDTO);
    }

}
