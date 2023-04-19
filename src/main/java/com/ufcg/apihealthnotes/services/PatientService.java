package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

   @Autowired
   private PatientRepository patientRepository;
   public Patient savePatient(PatientDTO patientDTO) {

   }
}
