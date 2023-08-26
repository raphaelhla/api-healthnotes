package com.ufcg.apihealthnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.apihealthnotes.entities.ChecklistItem;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long>{

}
