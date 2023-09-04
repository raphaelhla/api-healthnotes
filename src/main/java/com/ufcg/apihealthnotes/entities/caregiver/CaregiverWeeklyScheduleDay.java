//package com.ufcg.apihealthnotes.entities.caregiver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.ufcg.apihealthnotes.enums.DayOfWeek;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//@EqualsAndHashCode(of = "dayOfWeek")
//@Getter
//@Setter
//@Entity
//@Table(name = "caregiver_weekly_schedule_day")
//public class CaregiverWeeklyScheduleDay {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    
//    private DayOfWeek dayOfWeek; 
//    
//    @OneToMany(mappedBy = "weeklyScheduleDay", cascade = CascadeType.ALL)
//    private List<Appointment> dailyAppointmentsList;
//    
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id")
//    private CaregiverWeeklySchedule caregiverWeeklySchedule;
//
//    public CaregiverWeeklyScheduleDay() {
//        this.dailyAppointmentsList = new ArrayList<>();
//    }
//    
//	public CaregiverWeeklyScheduleDay(DayOfWeek dayOfWeek, CaregiverWeeklySchedule caregiverWeeklySchedule) {
//		this.dayOfWeek = dayOfWeek;
//        this.dailyAppointmentsList = new ArrayList<>();
//        this.caregiverWeeklySchedule = caregiverWeeklySchedule;
//	}
//	
//	public void addAppointment(Appointment newAppointment) {
//		this.dailyAppointmentsList.add(newAppointment);
//	}
//}