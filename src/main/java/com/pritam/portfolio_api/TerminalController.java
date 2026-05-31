package com.pritam.portfolio_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/terminal")
@CrossOrigin(origins = "https://portfolio-frontend-vert-alpha.vercel.app") // <-- Put your ACTUAL Vercel URL here!
public class TerminalController {

    // 1. Inject the Repository
    private final TerminalLogRepository logRepository;

    public TerminalController(TerminalLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    // --- NEW GET ENDPOINT ---
    @GetMapping("/history")
    public ResponseEntity<List<TerminalLog>> getTerminalHistory() {
        // Fetch the newest 20 logs from PostgreSQL
        List<TerminalLog> recentLogs = logRepository.findTop20ByOrderByTimestampDesc();
        return ResponseEntity.ok(recentLogs);
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody Map<String, String> payload) {
        String command = payload.getOrDefault("command", "").trim().toLowerCase();

        String response = switch (command) {
            case "help" -> "Available commands: status, whoami, skills --core, clear";
            case "status" -> "System Online. 3D Engine Active. Database connection established.";
            case "whoami" -> "Pritam - Software Engineer bridging complex computational logic with raw visual aesthetics.";
            case "skills --core" -> "Java, Spring Boot, SQL Database Management, Data Manipulation (Pandas/DSA). HackerRank 5-Star Rating.";
            case "sudo root" -> "ACCESS GRANTED. System Override Successful. \n\nDirect comms line opened: your.email@example.com \nGitHub: github.com/yourusername \n\n[ Achievement Unlocked: Beat the OS ]";
            case "" -> "";
            default -> "Command not found: " + command + ". Type 'help' for available commands.";
        };

        // 2. Save the interaction to the PostgreSQL Database
        if (!command.isEmpty()) {
            TerminalLog log = new TerminalLog(command, response);
            logRepository.save(log);
        }

        return ResponseEntity.ok(response);
    }
}