package com.ufcg.apihealthnotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.apihealthnotes.repositories.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public void deleteSchedule(Long id) {
		this.scheduleRepository.deleteById(id);
	}
}
