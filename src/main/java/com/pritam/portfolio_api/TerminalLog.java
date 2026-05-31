package com.pritam.portfolio_api;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "terminal_logs")
public class TerminalLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String command;

    @Column(columnDefinition = "TEXT") // Responses can be long, so we use TEXT instead of VARCHAR
    private String response;

    private LocalDateTime timestamp;

    // Default constructor is required by JPA
    public TerminalLog() {}

    // Custom constructor to easily create new logs
    public TerminalLog(String command, String response) {
        this.command = command;
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getCommand() { return command; }
    public String getResponse() { return response; }
    public LocalDateTime getTimestamp() { return timestamp; }
}