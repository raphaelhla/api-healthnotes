package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleDTO(Patient patient, LocalDate date, String time, String observation, String category) {
}
