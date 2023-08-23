package com.ufcg.apihealthnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.apihealthnotes.entities.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long>{

}
