package com.example.airline.repository;

import com.example.airline.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
    List<FlightSchedule> findByFlightIdAndDepartureTimeBetween(Long flightId, LocalDateTime from, LocalDateTime to);
}
