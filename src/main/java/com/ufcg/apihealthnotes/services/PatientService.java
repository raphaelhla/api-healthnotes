package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.entities.*;
import com.ufcg.apihealthnotes.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

   @Autowired
   private PatientRepository patientRepository;
   @Autowired
   private CaregiverService caregiverService;
   public Patient savePatient(PatientDTO patientDTO) {
      Caregiver caregiver = this.caregiverService.findCaregiver(patientDTO.getCaregiverCpf());
      Patient patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getPassword(), patientDTO.getBirthday(), caregiver);
      patient.updateFromDTO(patientDTO);
      return patientRepository.save(patient);
   }

   public List<Patient> getAllPatients() {
      return this.patientRepository.findAll();
   }

   public void deletePatient(String cpf) {
      this.patientRepository.deleteById(cpf);
   }

   public Patient updatePatient(PatientDTO patientDTO) {
      Patient patient = this.patientRepository.getById(patientDTO.getCpf());
      patient.updateFromDTO(patientDTO);
      this.patientRepository.save(patient);
      return patient;
   }
}
