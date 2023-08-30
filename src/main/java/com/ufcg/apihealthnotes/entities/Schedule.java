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
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Schedule implements Comparable<Schedule>{
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "calendarId")
    @JsonIgnore
    private Calendar calendar;

    private String time;
    private String description;

//    @ManyToOne
//	@JoinColumn(name = "caregiverCpf")
//    private Caregiver caregiver;
    
    private String caregiverId;

    public Schedule() {
    }

//    public Schedule(Calendar calendar, String time, String observation, String category, Caregiver caregiver) {
//        this.calendar = calendar;
//        this.time = time;
//        this.observation = observation;
//        this.category = category;
//        this.caregiver = caregiver;
//    }  
    
	public Schedule(Calendar calendar, String time, String description, String caregiverId) {
		this.calendar = calendar;
		this.time = time;
		this.description = description;
		this.caregiverId = caregiverId;
	}

	@Override
	public int compareTo(Schedule otherSchedule) {
		return this.time.compareTo(otherSchedule.time);
	}
}
