package com.usuarios.api_crud_usuarios.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized -> authorized
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        //Usuarios - criação a edição (implementar adm?)
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        //Agendamentos
                        .requestMatchers(HttpMethod.GET, "/agendamentos/cliente/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/agendamentos/profissional/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/agendamentos/**/cancelar").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/agendamentos/**/concluir").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
