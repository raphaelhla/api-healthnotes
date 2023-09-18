package com.ufcg.apihealthnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.apihealthnotes.services.CalendarService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin("*")
@RequestMapping("/calendar")
@SecurityRequirement(name = "bearer-key")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@DeleteMapping("/{calendarId}/schedule/{scheduleId}")
	public ResponseEntity<?> deleteSchedule(@PathVariable Long calendarId, @PathVariable Long scheduleId) {
		calendarService.deleteSchedule(calendarId, scheduleId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
