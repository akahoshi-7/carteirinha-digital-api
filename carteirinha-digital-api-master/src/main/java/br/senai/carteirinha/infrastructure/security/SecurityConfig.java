package br.senai.carteirinha.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity http
    ) throws Exception {

        http
            .csrf(
                csrf ->
                    csrf.disable()
            )
            .headers(
                headers ->
                    headers.frameOptions(
                        frame ->
                            frame.sameOrigin()
                    )
            )
            .authorizeHttpRequests(
                auth ->
                    auth
                        .requestMatchers(
                            "/auth/login",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/h2-console/**"
                        )
                        .permitAll()
                        .requestMatchers(
                            "/unidades-curriculares/**"
                        )
                        .authenticated()
                        .anyRequest()
                        .denyAll()
            )
            .oauth2ResourceServer(
                oauth2 ->
                    oauth2.jwt(
                        jwt -> {
                        }
                    )
            );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}