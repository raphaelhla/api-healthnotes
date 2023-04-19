package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Exam;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.Surgery;
import com.ufcg.apihealthnotes.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
      Caregiver caregiver = this.caregiverService.findCaregiver(patientDTO.getCpf());
      /*
      Patient patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getBirthday(),
              patientDTO.getMedicines(), patientDTO.getVaccines(), patientDTO.getSurgeries(),
              caregiver, patientDTO.getExams());

      */
      Patient patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getBirthday(), caregiver);
      List<Exam> exams = patientDTO.getExams().stream().map(e -> new Exam(patient, e.getCategory(), e.getDoctor(), e.getDescription())).collect(Collectors.toList());
      List<Surgery> surgeries = patientDTO.getSurgeries().stream().map(m -> new Surgery(patient, m.getDoctor(), m.getCause())).collect(Collectors.toList());
      patient.setExams(exams);
      patient.setMedicines(patientDTO.getMedicines());
      patient.setSurgeries(surgeries);
      patient.setVaccines(patientDTO.getVaccines());
      return patientRepository.save(patient);
   }
}
