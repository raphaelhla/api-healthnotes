package com.ufcg.apihealthnotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import com.ufcg.apihealthnotes.repositories.PatientRepository;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;
    
    @Autowired
    private PatientRepository patientRepository;

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

    public Caregiver getCaregiver(String cpf) {
        return this.caregiverRepository.findById(cpf).orElseThrow(() -> new IllegalArgumentException("Cuidador não encontrado"));
    }
    
    public void pararDeAcompanharPaciente(String caregiverCpf, String patientCpf) {
//        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Caregiver caregiver = caregiverRepository.findById(caregiverCpf).orElseThrow(() -> new IllegalArgumentException("Cuidador não encontrado"));
        Patient patient = patientRepository.findById(caregiverCpf).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        
        caregiver.getPatients().remove(patient);
        patient.getCaregivers().remove(caregiver);
        
        caregiverRepository.save(caregiver);
        patientRepository.save(patient);
    }
}
