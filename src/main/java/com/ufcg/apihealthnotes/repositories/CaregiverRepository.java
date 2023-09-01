package com.ufcg.apihealthnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ufcg.apihealthnotes.entities.caregiver.Caregiver;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, String> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
