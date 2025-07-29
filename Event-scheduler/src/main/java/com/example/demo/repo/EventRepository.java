package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByDate(LocalDate date);
    List<Event> findByTitleContainingIgnoreCase(String title);
}
