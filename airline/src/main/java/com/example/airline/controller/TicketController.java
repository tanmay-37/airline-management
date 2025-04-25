package com.example.airline.controller;

import com.example.airline.model.Ticket;
import com.example.airline.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestParam String passengerName, @RequestParam Long scheduleId) {
        return ticketService.createTicket(passengerName, scheduleId);
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return "Ticket cancelled successfully";
    }
}
