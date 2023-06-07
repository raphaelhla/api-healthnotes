package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.dto.*;
import com.ufcg.apihealthnotes.entities.Comorbidities;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.services.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity savePatient(@RequestBody PatientDTO patientDTO) {
        try {
            patientService.savePatient(patientDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getAllPatients() {
        try {
            Set<Patient> patients = patientService.findByCaregivers();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity getPatientByCpf(@PathVariable String cpf) {
        try {
            Patient patient = patientService.findByCpf(cpf);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity deletePatient(@PathVariable String cpf) {
        try {
            patientService.deletePatient(cpf);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updatePatient(@RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientService.updatePatient(patientDTO);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comorbidities/{id}")
    public ResponseEntity addComorbidities(@PathVariable String id, @RequestBody ComorbiditiesDTO comorbiditiesDTO) {
        try {
            patientService.addComorbidities(id, comorbiditiesDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/complexprocedures/{id}")
    public ResponseEntity addComplexProcedures(@PathVariable String id, @RequestBody ComplexpRroceduresDTO complexpRroceduresDTO) {
        try {
            patientService.addComplexProcedures(id, complexpRroceduresDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> addSchedule(@PathVariable String id, @RequestBody ScheduleDTO scheduleDTO) {
        try {
            patientService.addSchedule(id, scheduleDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}