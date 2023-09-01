//package com.ufcg.apihealthnotes.entities.caregiver;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.ufcg.apihealthnotes.enums.DayOfWeek;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "caregiver_weekly_schedule")
//public class CaregiverWeeklySchedule {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    
//    @OneToMany(mappedBy = "caregiverWeeklySchedule", cascade = CascadeType.ALL)
//    private Set<CaregiverWeeklyScheduleDay> weeklyScheduleDays;
//    
//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "caregiver_cpf")
//    private Caregiver caregiver;
//    
//    public CaregiverWeeklySchedule() {
////        initializeWeeklyScheduleDays();
//    }
//    
//    public CaregiverWeeklySchedule(Caregiver caregiver) {
//        initializeWeeklyScheduleDays();
//        this.caregiver = caregiver;
//    }
//
//    private void initializeWeeklyScheduleDays() {
//        this.weeklyScheduleDays = new HashSet<>();
//
//        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
//            CaregiverWeeklyScheduleDay weeklyScheduleDay = new CaregiverWeeklyScheduleDay(dayOfWeek, this);
//            this.weeklyScheduleDays.add(weeklyScheduleDay);
//        }
//    }
//}
//
