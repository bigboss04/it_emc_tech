package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite_places",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","place_id"}))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FavoritePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
