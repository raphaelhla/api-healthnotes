package com.ufcg.apihealthnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.apihealthnotes.dto.CaregiverAutenticationDTO;
import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.infra.security.TokenJWT;
import com.ufcg.apihealthnotes.infra.security.TokenService;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import com.ufcg.apihealthnotes.services.CaregiverService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping
public class UserController {

    @Autowired
    private CaregiverService caregiverService;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid CaregiverAutenticationDTO dadosAutenticacao) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.email(), dadosAutenticacao.password());
        var authentication = manager.authenticate(authenticationtoken);
        var caregiver = (Caregiver) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(caregiver);

        return ResponseEntity.status(HttpStatus.OK).body(new TokenJWT(tokenJWT, caregiver.getName(), caregiver.getCpf()));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> efetuarCadastro(@RequestBody @Valid CaregiverRegisterDTO dadosCadastro) {
        if (caregiverRepository.existsByEmail(dadosCadastro.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("An account with this Login already exists: " + dadosCadastro.email());
        }
        if (!(dadosCadastro.password().equals(dadosCadastro.confirmPassword()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The passwords do not match!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(caregiverService.saveCaregiver(dadosCadastro));
    }
}
