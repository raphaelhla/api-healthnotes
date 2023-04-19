package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Exam;
import com.ufcg.apihealthnotes.entities.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
