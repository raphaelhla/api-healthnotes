package com.ufcg.apihealthnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.dto.CaregiverUpdateDTO;
import com.ufcg.apihealthnotes.dto.NewPatientDTO;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.enums.DayOfWeek;
import com.ufcg.apihealthnotes.services.CaregiverService;
import com.ufcg.apihealthnotes.services.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin("*")
@RequestMapping("/caregiver")
@SecurityRequirement(name = "bearer-key")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;
    
    @Autowired
    private PatientService patientService;

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getCaregiverInfo(@PathVariable String cpf) {
            CaregiverDTO caregiver = caregiverService.getCaregiverInfo(cpf);
            return new ResponseEntity<>(caregiver, HttpStatus.OK);
    }
    
    @PutMapping("/{cpf}")
    public ResponseEntity<?> updateCaregiver(@PathVariable String cpf, @RequestBody CaregiverUpdateDTO caregiverUpdateDTO) {
            CaregiverDTO caregiver = caregiverService.updateCaregiver(cpf, caregiverUpdateDTO);
            return new ResponseEntity<>(caregiver, HttpStatus.OK);
    }
    
    @PostMapping("/{caregiverCpf}/patient")
    public ResponseEntity<?> savePatient(@PathVariable String caregiverCpf, @RequestBody NewPatientDTO newPatientDTO) {
        try {
            Patient patient = patientService.savePatient(newPatientDTO);
            patientService.addPatientInfo(newPatientDTO, caregiverCpf);
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{caregiverCpf}/patient/{patientCpf}")
    public ResponseEntity<?> pararDeAcompanharPaciente(@PathVariable String caregiverCpf, @PathVariable String patientCpf) {
        try {
            caregiverService.pararDeAcompanharPaciente(caregiverCpf, patientCpf);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{caregiverCpf}/number-patients")
    public ResponseEntity<?> getNumberPatients(@PathVariable String caregiverCpf) {
        try {
            Integer numPatients = caregiverService.getNumberPatients(caregiverCpf);
            return new ResponseEntity<>(numPatients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{caregiverCpf}/monthly-cost")
    public ResponseEntity<?> getMonthlyCost(@PathVariable String caregiverCpf) {
        try {
            Double monthlyCost = caregiverService.getMonthlyCost(caregiverCpf);
            return new ResponseEntity<>(monthlyCost, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{caregiverCpf}/appointments")
    public ResponseEntity<?> getAppointmentsOfDay(@PathVariable String caregiverCpf, @RequestParam DayOfWeek dayName) {
        try {
            return new ResponseEntity<>(caregiverService.getAppointmentsOfDay(dayName, caregiverCpf), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
