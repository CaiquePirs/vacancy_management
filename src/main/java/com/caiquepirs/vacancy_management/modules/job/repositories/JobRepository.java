package com.caiquepirs.vacancy_management.modules.job.repositories;

import com.caiquepirs.vacancy_management.modules.job.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID>, JpaSpecificationExecutor<JobEntity> {
    Page<JobEntity> findByCompanyId(UUID companyId, PageRequest pageRequest);
}
