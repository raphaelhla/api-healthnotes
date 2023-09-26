package com.ufcg.apihealthnotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufcg.apihealthnotes.entities.Calendar;
import com.ufcg.apihealthnotes.exception.paciente.CalendarNaoEncontradoException;
import com.ufcg.apihealthnotes.repositories.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private ScheduleService scheduleService;

	public void deleteCalendar(Calendar calendar) {
		this.calendarRepository.delete(calendar);
	}

//	@Transactional
	public void deleteSchedule(Long calendarId, Long scheduleId) {
		Calendar calendar = getCalendarById(calendarId);

		scheduleService.deleteSchedule(scheduleId);

		if (calendar.isEmpty()) {
			deleteCalendar(calendar);
		}

	}

	private Calendar getCalendarById(Long calendarId) {
        return this.calendarRepository.findById(calendarId).orElseThrow(() -> new CalendarNaoEncontradoException());

	}

}
