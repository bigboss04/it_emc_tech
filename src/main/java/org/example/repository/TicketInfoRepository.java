package org.example.repository;

import org.example.model.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInfoRepository extends JpaRepository<TicketInfo, Long> {
}
