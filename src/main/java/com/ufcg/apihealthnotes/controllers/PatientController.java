package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.services.CaregiverService;
import com.ufcg.apihealthnotes.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity saveCaregiverService(@RequestBody PatientDTO patientDTO) {
        try {
            patientService.savePatient(patientDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
