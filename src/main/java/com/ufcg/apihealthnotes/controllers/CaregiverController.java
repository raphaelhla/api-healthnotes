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
import org.springframework.web.bind.annotation.*;

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
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.email(), dadosAutenticacao.password());
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((Caregiver) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenJWT(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DataRegisterDTO dadosCadastro) {
        if (caregiverRepository.existsByEmail(dadosCadastro.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("An account with this Login already exists: " + dadosCadastro.email());
        }

        if (!(dadosCadastro.password().equals(dadosCadastro.confirmPassword()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The passwords do not match!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(caregiverService.saveCaregiver(dadosCadastro));
    }
}
