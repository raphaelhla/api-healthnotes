package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patientCpf")
    @JsonIgnore
    private Patient patient;

    private LocalDate date;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    public Calendar(Patient patient, LocalDate date) {
        this.patient = patient;
        this.date = date;
    }

    public Calendar(LocalDate date) {
        this.date = date;
    }

	@Override
	public String toString() {
		return "Calendar [schedules=" + schedules + "]";
	}
	
	public boolean isEmpty() {
		return this.schedules.size() == 0;
	}
    
    
}
