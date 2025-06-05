package com.usuarios.api_crud_usuarios.infra.security;

import com.usuarios.api_crud_usuarios.service.UsuarioService;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    //COM JWT - SECURITY
    //@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorized -> authorized
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//
//                        //rotas do swagger
//                        .requestMatchers(
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**",
//                                "/swagger-ui.html"
//                        ).permitAll()
//
//                        .requestMatchers(HttpMethod.POST, "/auth/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")
//
//                        //Usuarios - criação a edição (implementar adm?)
//                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarServicos").hasRole("CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarProfissionais").hasRole("CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/cliente/listarServicosEProfissionais").hasRole("CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/usuarios").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.PUT, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.DELETE, "/auth/usuarios/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//
//                        //Agendamentos
//                        .requestMatchers(HttpMethod.GET, "/auth/agendamentos/cliente/**").hasRole("CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/agendamentos/profissional/**").hasRole("PROFISSIONAL")
//                        .requestMatchers(HttpMethod.PUT, "/auth/agendamentos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.PUT, "/auth/agendamentos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//
//                        //serviços
//                        .requestMatchers(HttpMethod.GET, "/auth/servicos").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.POST, "/auth/servicos").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.PUT, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.DELETE, "/auth/servicos/**").hasAnyRole("PROFISSIONAL", "CLIENTE")
//
//
//                        //Disponibilidades
//                        .requestMatchers(HttpMethod.GET, "/auth/disponibilidades").hasAnyRole("PROFISSIONAL", "CLIENTE")
//                        .requestMatchers(HttpMethod.GET, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")
//                        .requestMatchers(HttpMethod.POST, "/auth/disponibilidades").hasRole("PROFISSIONAL")
//                        .requestMatchers(HttpMethod.PUT, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")
//                        .requestMatchers(HttpMethod.DELETE, "/auth/disponibilidades/**").hasRole("PROFISSIONAL")
//
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }


//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @SuppressWarnings("null")
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200");
//            }
//        };
//    }


}
