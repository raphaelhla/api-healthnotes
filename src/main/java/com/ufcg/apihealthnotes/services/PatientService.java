package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.*;
import com.ufcg.apihealthnotes.entities.*;
import com.ufcg.apihealthnotes.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

   @Autowired
   private PatientRepository patientRepository;

   @Autowired
   private CaregiverService caregiverService;

   public Patient savePatient(PatientDTO patientDTO) {
      Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      Patient patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getAge(), caregiver);
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

   public void addVaccine(String id, VaccineDTO vaccineDTO) {
      Patient patient = this.patientRepository.getById(id);
      Vaccine vaccine = new Vaccine(patient, vaccineDTO.getName(), vaccineDTO.getDescription());
      patient.getVaccines().add(vaccine);
      this.patientRepository.save(patient);
   }

   public void addMedicine(String id, MedicineDTO medicineDTO) {
      Patient patient = this.patientRepository.getById(id);
      Medicine medicine  = new Medicine(patient, medicineDTO.getName(), medicineDTO.getDescription());
      patient.getMedicines().add(medicine);
      this.patientRepository.save(patient);
   }

   public void addExam(String id, ExamDTO examDTO) {
      Patient patient = this.patientRepository.getById(id);
      Exam exam = new Exam(patient, examDTO.getCategory(), examDTO.getDoctor(), examDTO.getDescription());
      patient.getExams().add(exam);
      this.patientRepository.save(patient);
   }

   public void addSurgery(String id, SurgeryDTO surgeryDTO) {
      Patient patient = this.patientRepository.getById(id);
      Surgery surgery = new Surgery(patient, surgeryDTO.getDoctor(), surgeryDTO.getCause());
      patient.getSurgeries().add(surgery);
      this.patientRepository.save(patient);
   }

   public List<Patient> findByCaregiverCpf() {
      Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String cpf = caregiver.getCpf();
      return patientRepository.findByCaregiverCpf(cpf);
   }
}
