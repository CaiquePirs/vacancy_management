package com.caiquepirs.vacancy_management.config;

import com.caiquepirs.vacancy_management.security.SecurityCandidateFilter;
import com.caiquepirs.vacancy_management.security.SecurityCompanyFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityCompanyFilter securityFilter;
    private final SecurityCandidateFilter securityCandidateFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/candidate/auth/**").permitAll()
                            .requestMatchers(HttpMethod.POST,"/company/auth/**").permitAll()
                            .requestMatchers("/actuator/**").permitAll()
                            .requestMatchers(
                            "/v3/api-docs/**",
                            "/v3/api-docs.yaml",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/swagger-ui/index.html",
                            "/swagger-resources/**",
                            "/webjars/**"
                    ).permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
