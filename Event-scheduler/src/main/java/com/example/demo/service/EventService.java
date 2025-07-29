package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.EventDTO;

public interface EventService {
	EventDTO createEvent(EventDTO dto);
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    EventDTO updateEvent(Long id, EventDTO dto);
    void deleteEvent(Long id);
    List<EventDTO> searchByDate(LocalDate date);
    List<EventDTO> searchByTitle(String title);

}
