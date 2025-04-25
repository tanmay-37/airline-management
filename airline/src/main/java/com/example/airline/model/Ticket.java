package com.example.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;

    @ManyToOne
    @JoinColumn(name = "flight_schedule_id")
    private FlightSchedule schedule;
}
