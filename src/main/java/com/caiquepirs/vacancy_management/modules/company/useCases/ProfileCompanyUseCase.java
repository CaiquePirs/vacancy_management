package com.caiquepirs.vacancy_management.modules.company.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.company.entities.CompanyEntity;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.repositories.CompanyRepository;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public CompanyEntity getProfile(UUID id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Company not found"));
    }

    @Transactional
    public void deleteProfile(UUID id){
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Company not found"));

        List<JobEntity> jobs = new ArrayList<>(company.getVacanciesCreated());

        for(JobEntity job : jobs){
            job.getCandidates().clear();
            job.setCompany(null);
            jobRepository.delete(job);
        }

        companyRepository.delete(company);
    }
}
