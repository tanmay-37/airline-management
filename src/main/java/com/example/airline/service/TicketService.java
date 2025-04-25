package com.example.airline.service;

import com.example.airline.model.FlightSchedule;
import com.example.airline.model.Ticket;
import com.example.airline.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightScheduleService scheduleService;

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Transactional
    public Ticket createTicket(String passengerName, Long scheduleId) {
        if (ticketRepository.existsByPassengerNameAndScheduleId(passengerName, scheduleId)) {
            throw new RuntimeException("Duplicate ticket booking detected");
        }

        FlightSchedule schedule = scheduleService.getById(scheduleId);
        Ticket ticket = new Ticket();
        ticket.setPassengerName(passengerName);
        ticket.setSchedule(schedule);

        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }
}
