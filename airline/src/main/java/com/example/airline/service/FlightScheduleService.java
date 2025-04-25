package com.example.airline.service;

import com.example.airline.model.FlightSchedule;
import com.example.airline.repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightScheduleService {

    @Autowired
    private FlightScheduleRepository scheduleRepository;

    public List<FlightSchedule> getSchedules(Long flightId, LocalDate date) {
        if (date == null) {
            return scheduleRepository.findAll();
        }
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return scheduleRepository.findByFlightIdAndDepartureTimeBetween(flightId, start, end);
    }

    public FlightSchedule getById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
    }
}
