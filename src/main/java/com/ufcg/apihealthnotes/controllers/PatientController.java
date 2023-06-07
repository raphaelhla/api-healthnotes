package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.dto.*;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Medicine;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.Vaccine;
import com.ufcg.apihealthnotes.services.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getAllPatients() {
        try {
            Set<Patient> patients = patientService.findByCaregivers();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity getPatientByCpf(@PathVariable String cpf) {
        try {
            Patient patient = patientService.findByCpf(cpf);
            return new ResponseEntity<>(patient, HttpStatus.OK);
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

    @PutMapping("/vaccine/{id}")
    public ResponseEntity addVaccine(@PathVariable String id, @RequestBody VaccineDTO vaccineDTO) {
        try {
            patientService.addVaccine(id, vaccineDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/medicine/{id}")
    public ResponseEntity addMedicine(@PathVariable String id, @RequestBody MedicineDTO medicineDTO) {
        try {
            patientService.addMedicine(id, medicineDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/exam/{id}")
    public ResponseEntity addExam(@PathVariable String id, @RequestBody ExamDTO examDTO) {
        try {
            patientService.addExam(id, examDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/surgery/{id}")
    public ResponseEntity addSurgery(@PathVariable String id, @RequestBody SurgeryDTO surgeryDTO) {
        try {
            patientService.addSurgery(id, surgeryDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> addSchedule(@PathVariable String id, @RequestBody ScheduleDTO scheduleDTO) {
        try {
            patientService.addSchedule(id, scheduleDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}