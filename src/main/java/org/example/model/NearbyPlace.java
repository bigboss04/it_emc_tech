package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "nearby_places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearbyPlace {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    @ManyToOne
    @JoinColumn(name = "from_place_id")
    private Place fromPlace;

    @ManyToOne
    @JoinColumn(name = "to_place_id")
    private Place toPlace;

    private Double distanceKm;
}
