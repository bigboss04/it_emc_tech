package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.AccommodationType;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "accommodations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Acommondation {
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")
    private String id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String address;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    @ManyToMany
    @JoinTable(
            name = "accommodation_managers",
            joinColumns = @JoinColumn(name = "accommodation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private java.util.Set<User> managers;
}
