package com.caiquepirs.vacancy_management.docs;

import com.caiquepirs.vacancy_management.exceptions.ErrorResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import com.caiquepirs.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Candidate authentication", description = "endpoints to managing candidate authentication")
public interface CandidateAuthControllerDoc {


    @Operation(summary = "Candidate Login", description = "Candidate login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "login successful"),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 403,
                  "message": "Forbidden",
                  "errors": [
                    { "field": "Login", "message": "Username/Password incorrect" }
                  ]
                }
            """)
            ))
    })
    public ResponseEntity<AuthCandidateResponseDTO> login(@RequestBody @Valid AuthCandidateRequestDTO authCandidateDTO);

    @Operation(summary = "Candidate Register", description = "Candidate register")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidate successfully registered"),
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
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 400,
                  "message": "Validation failed",
                  "errors": [
                    { "field": "email", "message": "The field (email) must contain a valid email address." },
                    { "field": "username", "message": "Username is required" },
                    { "field": "name", "message": "Name is required." },
                    { "field": "curriculum", "message": "Curriculum is required." },
                    { "field": "description", "message": "Description is required." },
                    { "field": "password", "message": "Password must contain between 10 and 50 characters." }
                  ]
                }
            """)
            )),
    })
    public ResponseEntity<ProfileCandidateResponseDTO> register(@RequestBody @Valid ProfileCandidateRequestDTO candidateDTO);
}
