package com.caiquepirs.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object representing a company login")
public class AuthCompanyResponseDTO {

    @Schema(description = "Access token JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqb2JBUEkiLCJleHAiOjE3NTE1NjE5MTYsInN1YiI6IjdlNDJiZTE4LTIyMzYtNGFlMS1iY2ZkLTU1YjYyMDgxNTYyYSIsInJvbGVzIjpbIkNPTVBBTlkiXX0.ARhuXYJojDEHGC9yzngPQew0OEDJ6R72Fi-uR6bGmbY")
    private String access_token;

    @Schema(description = "Token expiration time", example = "1751561916465")
    private Long expire_at;

}
