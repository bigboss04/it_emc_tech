package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "favorite_places",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","place_id"}))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FavoritePlace {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
