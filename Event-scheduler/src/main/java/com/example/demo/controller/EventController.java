package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {
	
	@Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody EventDTO dto) {
        return new ResponseEntity<>(service.createEvent(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<EventDTO> getAll() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        return ResponseEntity.ok(service.updateEvent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/date")
    public List<EventDTO> searchByDate(@RequestParam LocalDate date) {
        return service.searchByDate(date);
    }

    @GetMapping("/search/title")
    public List<EventDTO> searchByTitle(@RequestParam String title) {
        return service.searchByTitle(title);
    }

}
