package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ticket_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketInfo {
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    // Gắn với Place
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private String ticketType;
    private String target;      // Đối tượng áp dụng
    private String timeSlot;    // Khung giờ
    @Column(columnDefinition = "TEXT")
    private String note;        // Ghi chú thêm
    private Double price;       // Giá vé

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
