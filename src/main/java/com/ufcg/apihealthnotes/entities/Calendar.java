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
@Table(name = "tb_calendar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "caregiverId")
    @JsonIgnore
    private Caregiver caregiver;

    private LocalDate date;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    public Calendar(Caregiver caregiver, LocalDate date) {
        this.caregiver = caregiver;
        this.date = date;
    }

    public Calendar(LocalDate date) {
        this.date = date;
    }
}
