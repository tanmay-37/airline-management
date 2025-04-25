package com.example.airline;

import com.example.airline.model.Flight;
import com.example.airline.model.FlightSchedule;
import com.example.airline.repository.FlightRepository;
import com.example.airline.repository.FlightScheduleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private FlightScheduleRepository scheduleRepo;

    @PostConstruct
    public void init() {
        Flight flight = new Flight();
        flight.setFlightNumber("AI202");
        flight.setOrigin("Delhi");
        flight.setDestination("Mumbai");

        Flight savedFlight = flightRepo.save(flight);

        FlightSchedule s1 = new FlightSchedule(null, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), savedFlight);
        FlightSchedule s2 = new FlightSchedule(null, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2), savedFlight);
        scheduleRepo.saveAll(List.of(s1, s2));
    }
}
