package com.ufcg.apihealthnotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ufcg.apihealthnotes.dto.CaregiverAutenticationDTO;
import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.entities.caregiver.Caregiver;
import com.ufcg.apihealthnotes.infra.security.TokenJWT;
import com.ufcg.apihealthnotes.infra.security.TokenService;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;

import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService {

	@Autowired
    private CaregiverService caregiverService;
	
    @Autowired
    private CaregiverRepository caregiverRepository;
    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return caregiverRepository.findByEmail(username);
    }
    
    public TokenJWT efetuarLogin(CaregiverAutenticationDTO dadosAutenticacao) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.email(), dadosAutenticacao.password());
        var authentication = manager.authenticate(authenticationtoken);
        var caregiver = (Caregiver) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(caregiver);

        return new TokenJWT(tokenJWT, caregiver.getName(), caregiver.getCpf());
    }

    public CaregiverDTO efetuarCadastro(@RequestBody @Valid CaregiverRegisterDTO dadosCadastro) {
        if (caregiverRepository.existsByEmail(dadosCadastro.email())) {
            throw new IllegalArgumentException("An account with this Login already exists: " + dadosCadastro.email());
        }
        if (!(dadosCadastro.password().equals(dadosCadastro.confirmPassword()))) {
        	throw new IllegalArgumentException("The passwords do not match!");
        }

        return caregiverService.saveCaregiver(dadosCadastro);
    }
}
