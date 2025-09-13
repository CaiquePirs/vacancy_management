package com.caiquepirs.vacancy_management.modules.job.specifications;

import com.caiquepirs.vacancy_management.modules.job.dto.JobFilterDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.enums.JobStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class JobSpecification {

    public static Specification<Job> filterBy(JobFilterDTO dto) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.description() != null && !dto.description().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + dto.description().toLowerCase() + "%"));
            }

            if (dto.companyName() != null && !dto.companyName().isBlank()) {
                var companyJoin = root.join("company");
                predicates.add(cb.like(cb.lower(companyJoin.get("name")), "%" + dto.companyName().toLowerCase() + "%"));
            }

            if (dto.benefits() != null && !dto.benefits().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("benefits")), "%" + dto.benefits().toLowerCase() + "%"));
            }

            if (dto.level() != null) {
                predicates.add(cb.like(cb.lower(root.get("level")), "%" + dto.level().toLowerCase() + "%"));
            }

            if (dto.minSalary() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), dto.minSalary()));
            }

            if (dto.maxSalary() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("salary"), dto.maxSalary()));
            }

            predicates.add(cb.equal(root.get("status"), JobStatus.ACTIVE));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

