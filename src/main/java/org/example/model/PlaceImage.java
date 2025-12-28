package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "place_images")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceImage {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
