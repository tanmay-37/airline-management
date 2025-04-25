package com.example.airline.repository;

import com.example.airline.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByPassengerNameAndScheduleId(String passengerName, Long scheduleId);
}
