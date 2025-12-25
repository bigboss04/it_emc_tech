package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_ratings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlaceRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int star;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
