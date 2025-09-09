package com.caiquepirs.vacancy_management.modules.candidate.useCases;

import com.caiquepirs.vacancy_management.exceptions.UserFoundException;
import com.caiquepirs.vacancy_management.exceptions.UserNotFoundException;
import com.caiquepirs.vacancy_management.modules.candidate.entities.Candidate;
import com.caiquepirs.vacancy_management.modules.candidate.mappers.CandidateMapper;
import com.caiquepirs.vacancy_management.modules.candidate.repositories.CandidateRepository;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.util.ValidateCandidateField;
import com.caiquepirs.vacancy_management.modules.job.dto.ApplyJobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.entities.Job;
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
        return repository.findById(idCandidate)
                .map(candidateMapper::toResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));
    }

    public void deleteProfile(UUID id){
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));
        repository.delete(candidate);
    }

    public ProfileCandidateResponseDTO updateProfile(UUID id, ProfileUpdateCandidateRequestDTO profileDTO) {
        Candidate existProfile = repository.findById(id).map(candidate -> {
            if (profileDTO.username() != null || profileDTO.email() != null) {
                repository.findByUsernameOrEmail(profileDTO.username(), profileDTO.email())
                        .ifPresent(userFound -> {
                            if (!userFound.getId().equals(candidate.getId())) {
                                throw new UserFoundException("This username or email is already in use");
                            }
                        });
            }
            Candidate candidateUpdated = validateCandidate.validate(candidate, profileDTO);
            return repository.save(candidateUpdated);

        }).orElseThrow(() -> new UserNotFoundException("Candidate ID not found"));
        return candidateMapper.toResponseDTO(existProfile);
    }

    public List<ApplyJobResponseDTO> jobApplications(UUID candidateId) {
        Candidate candidate = repository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException("Candidate not found"));

        List<Job> jobs = candidate.getJobApplications();

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
