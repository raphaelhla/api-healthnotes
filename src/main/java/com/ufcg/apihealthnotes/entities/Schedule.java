package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    public Schedule() {
    }

    public Schedule(Calendar calendar, String time, String observation, String category) {
        this.calendar = calendar;
        this.time = time;
        this.observation = observation;
        this.category = category;
    }
}
