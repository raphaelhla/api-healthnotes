package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Set<Patient> findByCaregivers(Caregiver caregiver);
}
