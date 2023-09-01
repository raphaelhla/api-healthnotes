package com.ufcg.apihealthnotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufcg.apihealthnotes.dto.AppointmentDTO;
import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.caregiver.Caregiver;
import com.ufcg.apihealthnotes.entities.caregiver.CaregiverPatient;
import com.ufcg.apihealthnotes.enums.DayOfWeek;
import com.ufcg.apihealthnotes.repositories.AppointmentRepository;
import com.ufcg.apihealthnotes.repositories.CaregiverPatientRepository;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import com.ufcg.apihealthnotes.repositories.PatientRepository;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
	@Autowired
	private CaregiverPatientRepository caregiverPatientRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Caregiver saveCaregiver(Caregiver caregiver) {
    	return caregiverRepository.save(caregiver);
    }
    
    @Transactional
    public CaregiverDTO saveCaregiver(CaregiverRegisterDTO dadosCadastro) {
    	String passwordCriptografado = passwordEncoder.encode(dadosCadastro.password());
        
    	Caregiver caregiver = new Caregiver(dadosCadastro);
        caregiver.setPassword(passwordCriptografado);
        
//        CaregiverWeeklySchedule caregiverWeeklySchedule = new CaregiverWeeklySchedule(caregiver);
//        caregiver.setWeeklySchedule(caregiverWeeklySchedule);
        
        caregiverRepository.save(caregiver);
        
        return new CaregiverDTO(caregiver);
    }

    public CaregiverDTO getCaregiverInfo(String caregiverCpf) {
		Caregiver caregiver = getCaregiver(caregiverCpf);
        return new CaregiverDTO(caregiver);
    }
    
    public void pararDeAcompanharPaciente(String caregiverCpf, String patientCpf) {
//        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Caregiver caregiver = caregiverRepository.findById(caregiverCpf).orElseThrow(() -> new IllegalArgumentException("Cuidador não encontrado"));
        Patient patient = patientRepository.findById(patientCpf).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        
//        caregiver.getPatients().remove(patient);
//        patient.getCaregivers().remove(caregiver);
        
        caregiverRepository.save(caregiver);
        patientRepository.save(patient);
    }

	public Integer getNumberPatients(String caregiverCpf) {
		Caregiver caregiver = getCaregiver(caregiverCpf);
		return caregiver.getNumberPatients();	
	}
	
	public Double getMonthlyCost(String caregiverCpf) {
		Double monthlyCost = 0.0;
		
		List<CaregiverPatient> caregiverPatients = caregiverPatientRepository.findByCaregiverCpf(caregiverCpf);
		
		for(CaregiverPatient cp : caregiverPatients) {
			monthlyCost += cp.getMonthlyCost();
		}
		
		return monthlyCost;
	}
	
    public Caregiver getCaregiver(String cpf) {
        return this.caregiverRepository.findById(cpf)
        		.orElseThrow(() -> new IllegalArgumentException("Cuidador não encontrado"));
    }
    
    public List<AppointmentDTO> getAppointmentsOfDay(DayOfWeek dayName, String caregiverCpf){
    	return appointmentRepository.findAllByDayOfWeekAndCaregiverCpf(dayName, caregiverCpf);
    }
}
