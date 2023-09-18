package com.ufcg.apihealthnotes.controllers;

import java.time.LocalDate;
import java.util.List;

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

import com.ufcg.apihealthnotes.dto.ChecklistItemDTO;
import com.ufcg.apihealthnotes.dto.ComorbiditiesDTO;
import com.ufcg.apihealthnotes.dto.ComplexProceduresDTO;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import com.ufcg.apihealthnotes.dto.ScheduleDTO;
import com.ufcg.apihealthnotes.entities.ChecklistItem;
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

	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		List<Patient> patients = patientService.findByCaregivers();
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<?> getPatientByCpf(@PathVariable String cpf) {
		Patient patient = patientService.getPatientByCpf(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<?> deletePatient(@PathVariable String cpf) {
		patientService.deletePatient(cpf);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping()
	public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO) {
		Patient patient = patientService.updatePatient(patientDTO);
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	@PutMapping("/comorbidities/{cpf}")
	public ResponseEntity<?> addComorbiditie(@PathVariable String cpf, @RequestBody ComorbiditiesDTO comorbiditiesDTO) {
		patientService.addComorbiditie(cpf, comorbiditiesDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/complexprocedures/{cpf}")
	public ResponseEntity<?> addComplexProcedure(@PathVariable String cpf,
			@RequestBody ComplexProceduresDTO complexpRroceduresDTO) {
		patientService.addComplexProcedure(cpf, complexpRroceduresDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/{cpfPatient}/add-schedule")
	public ResponseEntity<?> addSchedule(@PathVariable String cpfPatient, @RequestBody ScheduleDTO scheduleDTO) {
		patientService.addSchedule(cpfPatient, scheduleDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/{cpfPatient}/calendar/{data}")
	public ResponseEntity<?> getSchedulesByDate(@PathVariable String cpfPatient, @PathVariable LocalDate data) {
		List<Schedule> schedules = patientService.getSchedulesByDate(cpfPatient, data);
		return ResponseEntity.status(HttpStatus.OK).body(schedules);
	}

	@PostMapping("/{cpfPatient}/checklist")
	public ResponseEntity<?> addChecklistItem(@PathVariable String cpfPatient,
			@RequestBody ChecklistItemDTO checklistItemDTO) {
		ChecklistItem checklistItem = patientService.addChecklistItem(cpfPatient, checklistItemDTO);
		return ResponseEntity.status(HttpStatus.OK).body(checklistItem);

	}

	@GetMapping("/{cpfPatient}/checklist")
	public ResponseEntity<?> getChecklist(@PathVariable String cpfPatient) {
		List<ChecklistItem> checklist = patientService.getChecklistItem(cpfPatient);
		return ResponseEntity.status(HttpStatus.OK).body(checklist);

	}

	@DeleteMapping("/{cpfPatient}/checklist/{checklistItemId}")
	public ResponseEntity<?> deleteChecklistItem(@PathVariable String cpfPatient, @PathVariable Long checklistItemId) {
		patientService.deleteChecklistItem(cpfPatient, checklistItemId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/{cpfPatient}/checklist/{checklistItemId}")
	public ResponseEntity<?> updateChecklistItem(@PathVariable String cpfPatient, @PathVariable Long checklistItemId) {
		patientService.updateChecklistItem(cpfPatient, checklistItemId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}