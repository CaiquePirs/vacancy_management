package com.caiquepirs.vacancy_management.modules.job.repositories;

import com.caiquepirs.vacancy_management.modules.job.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    Page<JobEntity> findByCompanyId(UUID companyId, PageRequest pageRequest);
}
