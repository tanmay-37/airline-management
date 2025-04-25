package com.example.airline.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String origin;
    private String destination;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<FlightSchedule> schedules;
}
