package com.caiquepirs.vacancy_management.modules.candidate.entities;

import com.caiquepirs.vacancy_management.modules.job.entities.Job;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String description;
    private String curriculum;

    @ManyToMany
    @JoinTable(name = "candidate_job_applications",
              joinColumns = @JoinColumn(name = "candidate_id"),
              inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> jobApplications;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    public Candidate(){}
}
