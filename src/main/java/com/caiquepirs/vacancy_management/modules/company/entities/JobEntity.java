package com.caiquepirs.vacancy_management.modules.company.entities;

import com.caiquepirs.vacancy_management.modules.candidate.CandidateEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "This field is required")
    private String description;
    private String level;
    private String benefits;

    @NotBlank(message = "This field is required")
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity companyId;

    private UUID idCompany;

    @ManyToMany(mappedBy = "myJobApplications")
    private List<CandidateEntity> candidates;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

}
