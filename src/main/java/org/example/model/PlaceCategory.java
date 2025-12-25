package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlaceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Biển, Núi, Văn hóa...

    private String icon;
}
