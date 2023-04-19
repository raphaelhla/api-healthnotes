package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
