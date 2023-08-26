package com.ufcg.apihealthnotes.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.apihealthnotes.dto.ComorbiditiesDTO;
import com.ufcg.apihealthnotes.dto.ComplexProceduresDTO;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.dto.ScheduleDTO;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.Schedule;
import com.ufcg.apihealthnotes.services.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin("*")
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientService.savePatient(patientDTO);
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPatients() {
        try {
            Set<Patient> patients = patientService.findByCaregivers();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getPatientByCpf(@PathVariable String cpf) {
        try {
            Patient patient = patientService.getPatientByCpf(cpf);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deletePatient(@PathVariable String cpf) {
        try {
            patientService.deletePatient(cpf);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientService.updatePatient(patientDTO);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comorbidities/{cpf}")
    public ResponseEntity<?> addComorbiditie(@PathVariable String cpf, @RequestBody ComorbiditiesDTO comorbiditiesDTO) {
        try {
            patientService.addComorbiditie(cpf, comorbiditiesDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/complexprocedures/{cpf}")
    public ResponseEntity<?> addComplexProcedure(@PathVariable String cpf, @RequestBody ComplexProceduresDTO complexpRroceduresDTO) {
        try {
            patientService.addComplexProcedure(cpf, complexpRroceduresDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{cpfPatient}/add-schedule")
    public ResponseEntity<?> addSchedule(@PathVariable String cpfPatient, @RequestBody ScheduleDTO scheduleDTO) {
        try {
            patientService.addSchedule(cpfPatient, scheduleDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{cpfPatient}/calendar/{data}")
    public ResponseEntity<?> getSchedulesByDate(@PathVariable String cpfPatient, @PathVariable LocalDate data) {
        try {
        	List<Schedule> schedules = patientService.getSchedulesByDate(cpfPatient, data);
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}