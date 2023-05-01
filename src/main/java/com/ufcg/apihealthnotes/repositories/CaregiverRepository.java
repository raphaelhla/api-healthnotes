package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, String> {
    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);
}
