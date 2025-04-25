package com.example.airline.service;

import com.example.airline.model.Flight;
import com.example.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights(String sort) {
        return sort != null && sort.equalsIgnoreCase("asc")
                ? flightRepository.findAll().stream().sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).toList()
                : flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }
}
