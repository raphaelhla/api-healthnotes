package com.ufcg.apihealthnotes.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ufcg.apihealthnotes.dto.ComorbiditiesDTO;
import com.ufcg.apihealthnotes.dto.ComplexProceduresDTO;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.dto.ScheduleDTO;
import com.ufcg.apihealthnotes.entities.Calendar;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Comorbiditie;
import com.ufcg.apihealthnotes.entities.ComplexProcedure;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.Schedule;
import com.ufcg.apihealthnotes.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient savePatient(PatientDTO patientDTO) {
        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Patient patient;
        if (patientRepository.existsById(patientDTO.getCpf())) 
            patient = patientRepository.findById(patientDTO.getCpf()).get();
        else 
            patient = new Patient(patientDTO);
        

//        patient.updateFromDTO(patientDTO);
        patient.getCaregivers().add(caregiver);
        
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return this.patientRepository.findAll();
    }
    
    public Patient getPatientByCpf(String cpf) {
        return this.patientRepository.findById(cpf).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
    }

    public void deletePatient(String cpf) {
    	this.patientRepository.deleteById(cpf);
    }

    public Patient updatePatient(PatientDTO patientDTO) {
    	Patient patient = getPatientByCpf(patientDTO.getCpf());
    	patient.updateFromDTO(patientDTO);
        return patientRepository.save(patient);
    }

    public void addComplexProcedure(String cpf, ComplexProceduresDTO complexpRroceduresDTO) {
    	Patient patient = getPatientByCpf(cpf);
        ComplexProcedure complexProcedure = new ComplexProcedure(patient, complexpRroceduresDTO.description());
        patient.addComplexProcedure(complexProcedure);

        patientRepository.save(patient);
    }

    public void addComorbiditie(String cpf, ComorbiditiesDTO comorbiditiesDTO) {
    	Patient patient = getPatientByCpf(cpf);
        Comorbiditie comorbiditie = new Comorbiditie(patient, comorbiditiesDTO.description());
        patient.addComorbiditie(comorbiditie);
        
        patientRepository.save(patient);
    }

    public Set<Patient> findByCaregivers() {
        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return patientRepository.findByCaregivers(caregiver);
    }
    
    public void addSchedule(String cpf, ScheduleDTO scheduleDTO) {
    	Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = getPatientByCpf(cpf);
        LocalDate date = scheduleDTO.date();
        
        Calendar calendar;
        if (patient.getCalendar().containsKey(date)) { 
        	calendar = patient.getCalendar().get(date);
        } else { 
			calendar = new Calendar(patient, date);
			patient.getCalendar().put(date, calendar);
        }

        Schedule schedule = new Schedule(calendar, scheduleDTO.time(), scheduleDTO.observation(), scheduleDTO.category(), caregiver);
        calendar.getSchedules().add(schedule);
                
        patientRepository.save(patient);
    }


}
