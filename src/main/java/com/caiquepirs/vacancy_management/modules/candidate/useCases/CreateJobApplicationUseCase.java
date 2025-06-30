package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.DuplicateRecordException;
import com.caiquepirs.vacancy_management.exceptions.JobNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ApplyJobResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.enuns.JobStatus;
import com.caiquepirs.vacancy_management.modules.company.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateJobApplicationUseCase {

    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final ApplyJobResponseDTO jobResponseDTO;

    @Transactional
    public ApplyJobResponseDTO execute(UUID candidateId, UUID jobId){
        var job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found"));

        if(job.getStatus().equals(JobStatus.INACTIVE)){
            throw new JobNotFoundException("This job opening has been deactivated");
        }

        var candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(job.getCandidates().contains(candidate)){
            throw new DuplicateRecordException("User already registered for this vacancy");
        }

        job.setCandidates(List.of(candidate));
        var jobCreated = jobRepository.save(job);

        return  jobResponseDTO.execute(jobCreated, candidate);
    }
}
