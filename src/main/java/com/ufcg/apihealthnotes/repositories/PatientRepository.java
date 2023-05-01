package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query(value = "select * from TB_PATIENT where CAREGIVER_ID = :cpf", nativeQuery = true)
    List<Patient> findByCaregiverCpf(String cpf);
}
