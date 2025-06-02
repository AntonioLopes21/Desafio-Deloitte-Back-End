package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.infra.security.TokenService;
import com.usuarios.api_crud_usuarios.model.dto.AuthenticationDTO;
import com.usuarios.api_crud_usuarios.model.dto.LoginResponseDTO;
import com.usuarios.api_crud_usuarios.model.dto.RegisterDTO;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {

        //Autenticando senha
        var userPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto) {
        if(usuarioRepository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        //construtor da classe usuario personalizado
        Usuario novoUsuario = new Usuario(dto.email(), encryptedPassword, dto.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
