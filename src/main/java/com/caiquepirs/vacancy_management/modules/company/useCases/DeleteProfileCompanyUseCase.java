package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.Company;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import com.caiquepirs.vacancy_management.modules.company.validator.ValidateUpdateCompanyField;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import com.caiquepirs.vacancy_management.modules.job.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteProfileCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ValidateUpdateCompanyField validate;

    @Transactional
    public void execute(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Company ID not found"));

        List<Job> jobs = new ArrayList<>(company.getVacanciesCreated());

        for (Job job : jobs) {
            job.getCandidates().forEach(candidate -> candidate.getJobApplications().remove(job));

            job.getCandidates().clear();
            job.setCompany(null);
            jobRepository.delete(job);
        }
        companyRepository.delete(company);
    }

}
