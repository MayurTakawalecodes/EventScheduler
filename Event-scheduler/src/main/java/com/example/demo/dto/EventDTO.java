package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class EventDTO {
	
	private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String location;

}
