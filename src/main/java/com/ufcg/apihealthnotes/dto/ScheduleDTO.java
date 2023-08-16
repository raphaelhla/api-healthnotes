package com.ufcg.apihealthnotes.dto;

import java.time.LocalDate;

import com.ufcg.apihealthnotes.entities.Caregiver;

public record ScheduleDTO(Caregiver caregiver, LocalDate date, String time, String observation, String category) {
}
