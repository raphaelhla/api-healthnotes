package com.ufcg.apihealthnotes.dto;

import java.time.LocalTime;

import com.ufcg.apihealthnotes.enums.DayOfWeek;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayName;
    private String patientName;
    private String patientCpf;
    
    public AppointmentDTO(Long id, LocalTime startTime, LocalTime endTime, DayOfWeek dayName, String patientName, String patientCpf) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayName = dayName;
        this.patientName = patientName;
        this.patientCpf = patientCpf;
    }
}
