package com.ufcg.apihealthnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.services.CaregiverService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin("*")
@RequestMapping("/caregiver")
@SecurityRequirement(name = "bearer-key")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getCaregiver(@PathVariable String cpf) {
            Caregiver caregiver = caregiverService.getCaregiver(cpf);
            return new ResponseEntity<>(caregiver, HttpStatus.OK);
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
}
