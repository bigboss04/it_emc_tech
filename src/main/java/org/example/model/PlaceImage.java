package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_images")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
