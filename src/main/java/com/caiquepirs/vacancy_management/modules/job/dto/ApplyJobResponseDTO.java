package com.caiquepirs.vacancy_management.modules.job.dto;

import com.caiquepirs.vacancy_management.modules.job.enuns.JobStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Schema(description = "Data transfer object to return job data for which a candidate has applied")
public class ApplyJobResponseDTO {

    @Schema(description = "Company name", example = "Amazings Ltda")
    private String companyName;

    @Schema(description = "Job vacancy description", example = "Backend Software Developer Vacancy")
    private String jobDescription;

    @Schema(description = "Job vacancy level", example = "Junior")
    private String jobLevel;

    @Schema(description = "Candidate name", example = "Caique Pires")
    private String candidateName;

    @Schema(description = "Candidate email", example = "caique@gmail.com")
    private String candidateEmail;

    @Schema(description = "Job status", example = "ACTIVE")
    private JobStatus jobStatus;
}
