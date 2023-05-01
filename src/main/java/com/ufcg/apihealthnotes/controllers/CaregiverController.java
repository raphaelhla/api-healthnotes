package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.dto.DataAutenticationDTO;
import com.ufcg.apihealthnotes.dto.DataRegisterDTO;
import com.ufcg.apihealthnotes.infra.security.TokenJWT;
import com.ufcg.apihealthnotes.infra.security.TokenService;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import com.ufcg.apihealthnotes.services.CaregiverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DataAutenticationDTO dadosAutenticacao) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.password());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((Caregiver) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenJWT(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DataRegisterDTO dadosCadastro) {
        if (caregiverRepository.existsByLogin(dadosCadastro.login())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma conta com esse Login: " + dadosCadastro.login());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(caregiverService.saveCaregiver(dadosCadastro));
    }
}
