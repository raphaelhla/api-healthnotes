package com.ufcg.apihealthnotes.controllers;

import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.services.CaregiverService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @PostMapping
    public ResponseEntity saveCaregiverService(@RequestBody CaregiverDTO caregiverDTO) {
        try {
            caregiverService.saveCaregiver(caregiverDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findCaregiver(@PathVariable String id) {
        try {
            Caregiver caregiver = caregiverService.findCaregiver(id);
            return new ResponseEntity<>(caregiver, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
