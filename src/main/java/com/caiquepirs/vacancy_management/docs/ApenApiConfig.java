package com.caiquepirs.vacancy_management.docs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition( info = @Info(
        title = "Vacancy Management",
        version = "v1",
        description = "API to manage company job openings and candidate applications",
        contact = @Contact(
                email = "c.pires@ba.estudante.senai.br",
                name = "Caique Pires"
        )
))
public class ApenApiConfig {
}
