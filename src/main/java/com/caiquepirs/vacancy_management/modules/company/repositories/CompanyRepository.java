package com.caiquepirs.vacancy_management.modules.company.repositories;

import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByUsernameOrEmail(String username, String email);
    Optional<Company> findByUsername(String username);
}
