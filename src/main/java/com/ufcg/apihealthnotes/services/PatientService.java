package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.*;
import com.ufcg.apihealthnotes.entities.*;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import com.ufcg.apihealthnotes.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Transactional
    public Patient savePatient(PatientDTO patientDTO) {
        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient;

        if (patientRepository.existsById(patientDTO.getCpf())) {
            patient = patientRepository.findById(patientDTO.getCpf()).orElse(null);
        } else {
            patient = new Patient(patientDTO.getCpf(), patientDTO.getName(), patientDTO.getAge());
        }

        patient.getCaregivers().add(caregiver);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return this.patientRepository.findAll();
    }

    public void deletePatient(String cpf) {
        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findById(cpf).orElse(null);
        patient.getCaregivers().remove(caregiver);
        patientRepository.save(patient);
    }

    public Patient updatePatient(PatientDTO patientDTO) {
        Patient patient = this.patientRepository.getById(patientDTO.getCpf());

        patient.setAge(patientDTO.getAge());
        patient.setCpf(patientDTO.getCpf());
        patient.setName(patientDTO.getName());
        return patientRepository.save(patient);

    }

    public void addVaccine(String id, VaccineDTO vaccineDTO) {
        Patient patient = this.patientRepository.getById(id);
        Vaccine vaccine = new Vaccine(patient, vaccineDTO.getName(), vaccineDTO.getDescription());
        patient.getVaccines().add(vaccine);
        this.patientRepository.save(patient);
    }

    public void addMedicine(String id, MedicineDTO medicineDTO) {
        Patient patient = this.patientRepository.getById(id);
        Medicine medicine = new Medicine(patient, medicineDTO.getName(), medicineDTO.getDescription());
        patient.getMedicines().add(medicine);
        this.patientRepository.save(patient);
    }

    public void addExam(String id, ExamDTO examDTO) {
        Patient patient = this.patientRepository.getById(id);
        Exam exam = new Exam(patient, examDTO.getCategory(), examDTO.getDoctor(), examDTO.getDescription());
        patient.getExams().add(exam);
        this.patientRepository.save(patient);
    }

    public void addSurgery(String id, SurgeryDTO surgeryDTO) {
        Patient patient = this.patientRepository.getById(id);
        Surgery surgery = new Surgery(patient, surgeryDTO.getDoctor(), surgeryDTO.getCause());
        patient.getSurgeries().add(surgery);
        this.patientRepository.save(patient);
    }

    public Set<Patient> findByCaregivers() {
        Caregiver caregiver = (Caregiver) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return patientRepository.findByCaregivers(caregiver);
    }

    public void addSchedule(String id, ScheduleDTO scheduleDTO) {
        Patient patient = this.patientRepository.getById(id);

        boolean dateFound = false;

        for (Calendar calendar : patient.getCalendar()) {
            LocalDate date = calendar.getDate();

            if (date.equals(scheduleDTO.date())) {
                dateFound = true;
                var schedule = new Schedule(calendar, scheduleDTO.time(), scheduleDTO.observation(), scheduleDTO.category());
                calendar.getSchedules().add(schedule);
                break;
            }
        }

        if (!dateFound) {
            Calendar calendar = new Calendar(patient, scheduleDTO.date());
            var schedule = new Schedule(calendar, scheduleDTO.time(), scheduleDTO.observation(), scheduleDTO.category());
            calendar.getSchedules().add(schedule);
            patient.getCalendar().add(calendar);
        }
        patientRepository.save(patient);
    }
}
