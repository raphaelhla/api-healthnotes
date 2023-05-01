package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.entities.User;
import com.ufcg.apihealthnotes.dto.DataAutenticationDto;
import com.ufcg.apihealthnotes.dto.DataRegisterDto;
import com.ufcg.apihealthnotes.repositories.UserRepository;
import com.ufcg.apihealthnotes.infra.security.TokenJWT;
import com.ufcg.apihealthnotes.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AutenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DataAutenticationDto dadosAutenticacao) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenJWT(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DataRegisterDto dadosCadastro) {
        if (userRepository.existsByLogin(dadosCadastro.login())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma conta com esse Login: " + dadosCadastro.login());
        }
        User user = new User();
        user.setLogin(dadosCadastro.login());
        user.setSenha(passwordEncoder.encode(dadosCadastro.senha()));
        user.setNome(dadosCadastro.nome());
        user.setSobrenome(dadosCadastro.sobrenome());

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }
}
