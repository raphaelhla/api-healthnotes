package com.ufcg.apihealthnotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufcg.apihealthnotes.dto.AppointmentDTO;
import com.ufcg.apihealthnotes.entities.caregiver.Appointment;
import com.ufcg.apihealthnotes.enums.DayOfWeek;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

//    @Query("SELECT a FROM Appointment a WHERE a.dayName = :dayName AND a.caregiverPatient.caregiver.cpf = :caregiverCpf")
//	@Query("SELECT a FROM Appointment a "
//			+ "JOIN FETCH a.caregiverPatient cp "
//			+ "JOIN FETCH cp.caregiver c "
//			+ "JOIN FETCH cp.patient p "
//			+ "WHERE a.dayName = :dayName AND cp.caregiver.cpf = :caregiverCpf")
//	@Query("SELECT a FROM Appointment a " +
//	           "JOIN FETCH a.caregiverPatient cp " +
//	           "JOIN FETCH cp.caregiver c " +
//	           "LEFT JOIN FETCH cp.patient p " +
//	           "WHERE a.dayName = :dayName " +
//	           "AND c.cpf = :caregiverCpf")
//	List<Appointment> findAllByDayOfWeekAndCaregiverCpf(DayOfWeek dayName, String caregiverCpf);
	
	
	@Query("SELECT new com.ufcg.apihealthnotes.dto.AppointmentDTO("
			+ "a.id, a.startTime, a.endTime, a.dayName, p.name, p.cpf) "
			+ "FROM Appointment a "
			+ "JOIN a.caregiverPatient cp "
			+ "JOIN cp.patient p "
			+ "WHERE a.dayName = :dayName AND cp.caregiver.cpf = :caregiverCpf")
	List<AppointmentDTO> findAllByDayOfWeekAndCaregiverCpf(DayOfWeek dayName, String caregiverCpf);
}
