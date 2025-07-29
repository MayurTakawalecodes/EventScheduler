package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EventDTO;
import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
    private EventRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EventDTO createEvent(EventDTO dto) {
        Event event = mapper.map(dto, Event.class);
        return mapper.map(repository.save(event), EventDTO.class);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(event -> mapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return mapper.map(event, EventDTO.class);
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        mapper.map(dto, event); // update fields from DTO to existing entity

        return mapper.map(repository.save(event), EventDTO.class);
    }

    @Override
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EventDTO> searchByDate(LocalDate date) {
        return repository.findByDate(date)
                .stream()
                .map(event -> mapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(event -> mapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

}
