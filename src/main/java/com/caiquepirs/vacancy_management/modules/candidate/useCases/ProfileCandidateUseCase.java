package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.util.ValidateCandidateField;
import com.caiquepirs.vacancy_management.modules.job.dto.ApplyJobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.JobEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileCandidateUseCase {

    private final CandidateRepository repository;
    private final CandidateMapper candidateMapper;
    private final ValidateCandidateField validateCandidate;

    public ProfileCandidateResponseDTO getProfile(UUID idCandidate){
        var candidate = repository.findById(idCandidate)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return candidateMapper.toResponseDTO(candidate);
    }

    public void deleteProfile(UUID id){
        var existProfile = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        repository.delete(existProfile);
    }

    public ProfileCandidateResponseDTO updateProfile(UUID id, ProfileUpdateCandidateRequestDTO profileDTO) {
        var existProfile = repository.findById(id).map(candidate -> {

            if (profileDTO.username() != null || profileDTO.email() != null) {
                repository.findByUsernameOrEmail(profileDTO.username(), profileDTO.email())
                        .ifPresent(userFound -> {
                            if (!userFound.getId().equals(candidate.getId())) {
                                throw new UserFoundException("This username or email is already in use");
                            }
                        });
            }

            var candidateUpdated = validateCandidate.validate(candidate, profileDTO);
            return repository.save(candidateUpdated);

        }).orElseThrow(() -> new UserNotFoundException("User not found"));
        return candidateMapper.toResponseDTO(existProfile);
    }

    public List<ApplyJobResponseDTO> jobApplications(UUID candidateId) {
        var candidate = repository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException("Candidate not found"));

        List<JobEntity> jobs = candidate.getJobApplications();

        return jobs.stream()
                .map(job -> ApplyJobResponseDTO.builder()
                        .companyName(job.getCompany().getName())
                        .jobDescription(job.getDescription())
                        .jobLevel(job.getLevel())
                        .candidateName(candidate.getName())
                        .candidateEmail(candidate.getEmail())
                        .jobStatus(job.getStatus())
                        .build())
                .toList();
    }

}
