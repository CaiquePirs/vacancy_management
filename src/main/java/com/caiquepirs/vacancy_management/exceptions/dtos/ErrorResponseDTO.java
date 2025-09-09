package com.caiquepirs.vacancy_management.exceptions.dtos;

import java.util.List;

public record ErrorResponseDTO(int status, String message, List<ErrorMessageDTO> erros) {
}
