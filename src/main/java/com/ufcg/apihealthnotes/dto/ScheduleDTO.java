package com.ufcg.apihealthnotes.dto;

import java.time.LocalDate;

public record ScheduleDTO(LocalDate date, String time, String observation, String category) {
}
