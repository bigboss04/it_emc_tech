package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "place_tags")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceTag {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Place> places;
}
