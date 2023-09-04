package com.ufcg.apihealthnotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.apihealthnotes.entities.caregiver.CaregiverPatient;

@Repository
public interface CaregiverPatientRepository extends JpaRepository<CaregiverPatient, Long>{

	List<CaregiverPatient> findByCaregiverCpf(String cpf);

}
