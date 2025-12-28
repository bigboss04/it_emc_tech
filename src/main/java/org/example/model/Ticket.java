package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    private Double basePrice;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketInfo> ticketInfos;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
