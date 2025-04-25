package com.example.airline.controller;

import com.example.airline.model.FlightSchedule;
import com.example.airline.service.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights/{flightId}/schedules")
public class FlightScheduleController {

    @Autowired
    private FlightScheduleService scheduleService;

    @GetMapping
    public List<FlightSchedule> getSchedules(
            @PathVariable Long flightId,
            @RequestParam(required = false) String date
    ) {
        LocalDate parsedDate = (date != null) ? LocalDate.parse(date) : null;
        return scheduleService.getSchedules(flightId, parsedDate);
    }
}
