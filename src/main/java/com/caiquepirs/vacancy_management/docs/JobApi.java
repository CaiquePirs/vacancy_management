package com.caiquepirs.vacancy_management.docs;

import com.caiquepirs.vacancy_management.exceptions.dtos.ErrorResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobFilterDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobResponseDTO;
import com.caiquepirs.vacancy_management.modules.job.dto.JobUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Job", description = "Endpoint for companies to manage their created job vacancies")
public interface JobApi {

    @Operation(summary = "Create Job", description = "Create a job vacancy for a company logged into the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Job vacancy created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 400,
                  "message": "Validation failed",
                  "errors": [
                    { "field": "description", "message": "Job description is required." },
                    { "field": "benefits", "message": "Job benefits is required." },
                    { "field": "level", "message": "Job level is required."},
                    { "field": "salary", "message": "Salary is required."},
                    { "field": "salary", "message": "Salary format is invalid."},
                    { "field": "salary", "message": "Salary must be greater than 0."}
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Company not found",
                  "errors": [
                    { "field": "company", "message": "Company not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<JobResponseDTO> create(@RequestBody @Valid JobCreateRequestDTO jobDTO);


    @Operation(summary = "List Jobs of a company", description = "Displays the list of job openings created by a company")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "job list found successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Company not found",
                  "errors": [
                    { "field": "company", "message": "Company not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<JobResponseDTO>> listJobsByCompany(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size);




    @Operation(summary = "List available Jobs", description = "Allow searching for job vacancies in the system through filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Job vacancies found successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 400,
                  "message": "Search failed",
                  "errors": [
                    { "field": "salary", "message": "Salary format is invalid."},
                    { "field": "salary", "message": "Salary must be greater than 0."}
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<JobResponseDTO>> filterJobs(@ModelAttribute JobFilterDTO filterDTO,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size);


    @Operation(summary = "Delete job", description = "delete a job opening by job ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "job vacancy successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Job not found", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class),
                    examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Job not found",
                  "errors": [
                    { "field": "Job", "message": "Job not found" }
                  ]
                }
            """)
            )),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID jobId);

    @Operation(
            summary = "Update Job",
            description = "Update the details of a job opening by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JobResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job or Company not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class),
                            examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Job not found",
                  "errors": [
                    { "field": "job", "message": "Job not found" }
                  ]
                }
            """)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> update(@RequestBody @Valid JobUpdateRequestDTO jobDTO, @PathVariable(name = "id") UUID jobId);

    @Operation(
            summary = "Toggle Job Status",
            description = "Activate or deactivate a job opening by job ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Job status successfully toggled",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Job or Company not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class),
                            examples = @ExampleObject(value = """
                {
                  "status": 404,
                  "message": "Job not found",
                  "errors": [
                    { "field": "job", "message": "Job not found" }
                  ]
                }
            """)
                    )
            ),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleJobStatus(@PathVariable(name = "id") UUID jobId);

}
