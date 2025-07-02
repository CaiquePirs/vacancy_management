package com.caiquepirs.vacancy_management.modules.company.dto;

import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import lombok.*;

@Getter
@Builder
public class ApplyJobResponseDTO {
    private String companyName;
    private String jobDescription;
    private String jobLevel;
    private String candidateName;
    private String candidateEmail;
    private JobStatus jobStatus;
}
