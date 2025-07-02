package com.caiquepirs.vacancy_management.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "company")
    private List<JobEntity> vacanciesCreated;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    public CompanyEntity(){}


}
