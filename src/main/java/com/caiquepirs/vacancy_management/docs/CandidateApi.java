package com.caiquepirs.vacancy_management.docs;

import com.caiquepirs.vacancy_management.exceptions.dtos.ErrorResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileUpdateCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.ApplyJobResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Candidate", description = "Endpoints to managing candidates")
public interface CandidateApi {


    @Operation(summary = "Get Profile", description = "Get the candidate profile data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile found successfully"),
            @ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Candidate not found",
                  "errors": [
                    { "field": "candidate", "message": "Candidate not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ProfileCandidateResponseDTO> getProfile(HttpServletRequest request);



    @Operation(summary = "Delete Profile", description = "Delete candidate profile data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Candidate not found",
                  "errors": [
                    { "field": "candidate", "message": "Candidate not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)

    })
    public ResponseEntity<Void> deleteProfile(HttpServletRequest request);



    @Operation(summary = "Update Profile", description = "Update candidate profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
            @ApiResponse(responseCode = "409", description = "Conflict Email or Username", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 409,
                  "message": "This User already exist",
                  "errors": [
                    { "field": "candidate", "message": "This User already exist" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Candidate not found",
                  "errors": [
                    { "field": "candidate", "message": "Candidate not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)

    })
    public ResponseEntity<ProfileCandidateResponseDTO> updateProfile(HttpServletRequest request,
                                                                     @RequestBody @Valid ProfileUpdateCandidateRequestDTO profileDTO);


    @Operation(summary = "Apply for a Job", description = "Allows the candidate to apply for a job vacancy")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Application completed successfully"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 409,
                  "message": "User already registered for this vacancy",
                  "errors": [
                    { "field": "Job", "message": "User already registered for this vacancy" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Candidate not found",
                  "errors": [
                    { "field": "candidate", "message": "Candidate not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> applyToJob(@PathVariable UUID id, HttpServletRequest request);


    @Operation(summary = "List of applications by candidate", description = "Displays the list of job applications made by the candidate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List successfully searched"),
            @ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Candidate not found",
                  "errors": [
                    { "field": "candidate", "message": "Candidate not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<List<ApplyJobResponseDTO>> getJobApplications(HttpServletRequest request);
}
