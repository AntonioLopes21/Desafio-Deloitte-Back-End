package com.usuarios.api_crud_usuarios.infra.security;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized -> authorized
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/auth/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        //Usuarios - criação a edição (implementar adm?)
                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarServicos").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarProfissionais").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarServicosEProfissionais").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        //Agendamentos
                        .requestMatchers(HttpMethod.GET, "/auth/agendamentos/cliente/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/agendamentos/profissional/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PUT, "/auth/agendamentos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/auth/agendamentos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")

                        //serviços
                        .requestMatchers(HttpMethod.GET, "/auth/servicos").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/auth/servicos").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")


                        //Disponibilidades
                        .requestMatchers(HttpMethod.GET, "/auth/disponibilidades").hasAnyRole("PROFISSIONAL", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.POST, "/auth/disponibilidades").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PUT, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.DELETE, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")

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
