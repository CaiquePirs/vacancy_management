package com.caiquepirs.vacancy_management.docs;

import com.caiquepirs.vacancy_management.exceptions.ErrorResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyUpdateRequestDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Company", description = "Endpoints to managing company")
public interface CompanyControllerDoc {


    @Operation(summary = "Get Company Profile", description = "Find company profile by company ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company found successfully"),
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
    @GetMapping
    public ResponseEntity<CompanyResponseDTO> getProfile(HttpServletRequest request);



    @Operation(summary = "Update Company Profile", description = "Updates company data by company ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
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
    @PutMapping
    public ResponseEntity<CompanyResponseDTO> updateProfile(@RequestBody @Valid CompanyUpdateRequestDTO companyDTO,
                                                            HttpServletRequest request);




    @Operation(summary = "Delete Company Profile", description = "Delete company profile by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Company deleted successfully", content = @Content),
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
    public ResponseEntity<Void> deleteProfile(HttpServletRequest request);

}
