package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {}
