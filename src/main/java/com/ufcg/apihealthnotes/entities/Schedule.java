package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_schedule")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Schedule {
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "calendarId")
    @JsonIgnore
    private Calendar calendar;

    private String time;
    private String observation;
    private String category;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnore
    private Patient patient;

    @ManyToOne
	@JoinColumn(name = "caregiverId")
	@JsonIgnore    
    private Caregiver caregiver;

    public Schedule() {
    }

    public Schedule(Calendar calendar, String time, String observation, String category, Patient patient, Caregiver caregiver) {
        this.calendar = calendar;
        this.time = time;
        this.observation = observation;
        this.category = category;
        this.patient = patient;
        this.caregiver = caregiver;
    }

//    public Schedule(Patient patient,Calendar calendar, String time, String observation, String category) {
//        this.patient = patient;
//        this.calendar = calendar;
//        this.time = time;
//        this.observation = observation;
//        this.category = category;
//    }
}
