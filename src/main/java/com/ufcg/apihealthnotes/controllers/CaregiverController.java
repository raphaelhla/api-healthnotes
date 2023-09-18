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
		return ResponseEntity.status(HttpStatus.OK).body(caregiver);
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<?> updateCaregiver(@PathVariable String cpf,
			@RequestBody CaregiverUpdateDTO caregiverUpdateDTO) {
		CaregiverDTO caregiver = caregiverService.updateCaregiver(cpf, caregiverUpdateDTO);
		return ResponseEntity.status(HttpStatus.OK).body(caregiver);
	}

	@PostMapping("/{caregiverCpf}/patient")
	public ResponseEntity<?> savePatient(@PathVariable String caregiverCpf, @RequestBody NewPatientDTO newPatientDTO) {
		Patient patient = patientService.savePatient(newPatientDTO);
		patientService.addPatientInfo(newPatientDTO, caregiverCpf);
		return ResponseEntity.status(HttpStatus.CREATED).body(patient);
	}

	@PutMapping("/{caregiverCpf}/patient/{patientCpf}")
	public ResponseEntity<?> pararDeAcompanharPaciente(@PathVariable String caregiverCpf,
			@PathVariable String patientCpf) {
		caregiverService.pararDeAcompanharPaciente(caregiverCpf, patientCpf);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/{caregiverCpf}/number-patients")
	public ResponseEntity<?> getNumberPatients(@PathVariable String caregiverCpf) {
		Integer numPatients = caregiverService.getNumberPatients(caregiverCpf);
		return ResponseEntity.status(HttpStatus.OK).body(numPatients);
	}

	@GetMapping("/{caregiverCpf}/monthly-cost")
	public ResponseEntity<?> getMonthlyCost(@PathVariable String caregiverCpf) {
		Double monthlyCost = caregiverService.getMonthlyCost(caregiverCpf);
		return ResponseEntity.status(HttpStatus.OK).body(monthlyCost);
	}

	@GetMapping("/{caregiverCpf}/appointments")
	public ResponseEntity<?> getAppointmentsOfDay(@PathVariable String caregiverCpf, @RequestParam DayOfWeek dayName) {
		return ResponseEntity.status(HttpStatus.OK).body(caregiverService.getAppointmentsOfDay(dayName, caregiverCpf));
	}
}
