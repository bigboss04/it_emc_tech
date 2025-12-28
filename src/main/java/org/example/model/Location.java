package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String address;
    private Double latitude;
    private Double longitude;
}
