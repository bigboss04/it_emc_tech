package org.example.repository;

import org.example.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

interface TicketRepository extends JpaRepository<Ticket, Long> {
}
