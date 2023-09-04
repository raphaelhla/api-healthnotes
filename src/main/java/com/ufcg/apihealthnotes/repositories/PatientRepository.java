package com.ufcg.apihealthnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.apihealthnotes.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
