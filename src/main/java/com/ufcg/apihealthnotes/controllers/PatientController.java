package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity savePatient(@RequestBody PatientDTO patientDTO) {
        try {
            patientService.savePatient(patientDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity deletePatient(@PathVariable String cpf) {
        try {
            patientService.deletePatient(cpf);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity updatePatient(@RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientService.updatePatient(patientDTO);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
