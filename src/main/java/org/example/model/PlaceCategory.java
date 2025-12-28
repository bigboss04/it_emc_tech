package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "place_categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlaceCategory {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    private String name; // Biển, Núi, Văn hóa...

    private String icon;

    @Builder.Default
    private boolean deleted = false; // Soft delete flag
}
