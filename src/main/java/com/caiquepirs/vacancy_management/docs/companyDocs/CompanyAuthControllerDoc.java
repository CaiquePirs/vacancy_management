package com.caiquepirs.vacancy_management.docs.companyDocs;

import com.caiquepirs.vacancy_management.exceptions.ErrorResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.AuthCompanyResponseDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyCreateRequestDTO;
import com.caiquepirs.vacancy_management.modules.company.dto.CompanyResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;

@Tag(name = "Company authentication", description = "endpoints for managing company authentication")
public interface CompanyAuthControllerDoc {

    @Operation(summary = "Company Login", description = "company login")
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
    @PostMapping("/login")
    public ResponseEntity<AuthCompanyResponseDTO> login(@RequestBody AuthCompanyRequestDTO companyDTO)  throws AuthenticationException;




    @Operation(summary = "Company Register", description = "company register")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company successfully registered"),
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
                    { "field": "website", "message": "Website is required." },
                    { "field": "description", "message": "Description is required." },
                    { "field": "password", "message": "Password must contain between 10 and 50 characters." }
                  ]
                }
            """)
            )),

    })
    public ResponseEntity<CompanyResponseDTO> register(@RequestBody @Valid CompanyCreateRequestDTO companyCreateRequestDTO);

}
