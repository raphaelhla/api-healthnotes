package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.dto.VaccineDTO;
import com.ufcg.apihealthnotes.entities.*;
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
      Caregiver caregiver = this.caregiverService.findCaregiver(patientDTO.getCaregiverCpf());
      Patient patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getPassword(), patientDTO.getBirthday(), caregiver);

      List<Exam> exams = patientDTO.getExams().stream().map(e -> new Exam(patient, e.getCategory(), e.getDoctor(), e.getDescription()))
              .collect(Collectors.toList());

      List<Surgery> surgeries = patientDTO.getSurgeries().stream().map(s -> new Surgery(patient, s.getDoctor(), s.getCause()))
              .collect(Collectors.toList());

      List<Medicine> medicines = patientDTO.getMedicines().stream().map(m -> new Medicine(patient, m.getName(), m.getDescription()))
              .collect(Collectors.toList());

      List<Vaccine> vaccines = patientDTO.getVaccines().stream().map(v -> new Vaccine(patient, v.getName(), v.getDescription()))
              .collect(Collectors.toList());

      patient.setExams(exams);
      patient.setMedicines(medicines);
      patient.setSurgeries(surgeries);
      patient.setVaccines(vaccines);
      return patientRepository.save(patient);
   }
}
