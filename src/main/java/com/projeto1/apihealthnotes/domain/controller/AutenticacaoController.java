package com.projeto1.apihealthnotes.domain.controller;

import com.projeto1.apihealthnotes.domain.model.Usuario;
import com.projeto1.apihealthnotes.domain.model.dto.DadosAutenticacaoDto;
import com.projeto1.apihealthnotes.domain.model.dto.DadosCadastroDto;
import com.projeto1.apihealthnotes.domain.repository.UsuarioRepository;
import com.projeto1.apihealthnotes.infra.security.DadosTokenJWT;
import com.projeto1.apihealthnotes.infra.security.TokenService;
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
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dadosAutenticacao) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DadosCadastroDto dadosCadastro) {
        if (usuarioRepository.existsByLogin(dadosCadastro.login())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma conta com esse Login: " + dadosCadastro.login());
        }
        Usuario user = new Usuario();
        user.setLogin(dadosCadastro.login());
        user.setSenha(passwordEncoder.encode(dadosCadastro.senha()));
        user.setNome(dadosCadastro.nome());
        user.setSobrenome(dadosCadastro.sobrenome());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(user));
    }
}
