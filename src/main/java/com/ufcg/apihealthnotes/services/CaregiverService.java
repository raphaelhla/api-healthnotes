package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Caregiver saveCaregiver(CaregiverRegisterDTO dadosCadastro) {
        Caregiver caregiver = new Caregiver();
        caregiver.setEmail(dadosCadastro.email());
        caregiver.setPassword(passwordEncoder.encode(dadosCadastro.password()));
        caregiver.setCpf(dadosCadastro.cpf());
        caregiver.setName(dadosCadastro.name());
        caregiver.setLastname(dadosCadastro.lastname());

        return this.caregiverRepository.save(caregiver);
    }

    public Caregiver findCaregiver(String caregiverCpf) {
        Caregiver caregiver = this.caregiverRepository.findById(caregiverCpf).get();
        return caregiver;
    }
}
