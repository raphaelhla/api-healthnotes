package com.ufcg.apihealthnotes.dto;

import java.util.Map;

import com.ufcg.apihealthnotes.entities.caregiver.Appointment;
import com.ufcg.apihealthnotes.enums.DayOfWeek;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaregiverPatientDTO {

    private Double monthlyCost;
    private Map<DayOfWeek, Appointment> appointmentDays;
}
