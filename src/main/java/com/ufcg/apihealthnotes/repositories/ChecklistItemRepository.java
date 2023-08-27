package com.ufcg.apihealthnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.apihealthnotes.entities.ChecklistItem;
import com.ufcg.apihealthnotes.entities.Patient;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long>{

    ChecklistItem findByIdAndPatient(Long itemId, Patient patient);

}
