package com.pritam.portfolio_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TerminalLogRepository extends JpaRepository<TerminalLog, Long> {
    
    // Spring automatically translates this into: 
    // SELECT * FROM terminal_logs ORDER BY timestamp DESC LIMIT 20;
    List<TerminalLog> findTop20ByOrderByTimestampDesc();
}