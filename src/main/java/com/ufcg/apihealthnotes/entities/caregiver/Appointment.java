package com.ufcg.apihealthnotes.entities.caregiver;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.apihealthnotes.enums.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    private LocalTime endTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "day_name")
    private DayOfWeek dayName;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "caregiver_patient_id")
    private CaregiverPatient caregiverPatient;

	public Appointment(LocalTime startTime, LocalTime endTime, CaregiverPatient caregiverPatient) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.caregiverPatient = caregiverPatient;
	}
    
    
}
