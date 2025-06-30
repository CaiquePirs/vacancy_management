package com.caiquepirs.vacancy_management.modules.candidate.dto;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateEntity;
import com.caiquepirs.vacancy_management.modules.company.entities.JobEntity;
import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ApplyJobResponseDTO {

    private String companyName;
    private String jobDescription;
    private String jobLevel;
    private String candidateName;
    private String candidateEmail;
    private JobStatus jobStatus;

    public ApplyJobResponseDTO execute(JobEntity job, CandidateEntity candidate){
        return ApplyJobResponseDTO.builder()
                .candidateName(job.getCompanyId().getName())
                .jobDescription(job.getDescription())
                .jobLevel(job.getLevel())
                .candidateName(candidate.getName())
                .candidateEmail(candidate.getEmail())
                .jobStatus(job.getStatus())
                .build();
    }
}
