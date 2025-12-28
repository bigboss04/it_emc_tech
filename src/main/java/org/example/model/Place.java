package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "places")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String address;
    private Double latitude;
    private Double longitude;
    private String openingHours;
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PlaceCategory category;

    private Double avgRating;
    private Integer ratingCount;

    private String crowdStatus;
    private LocalDateTime crowdUpdatedAt;

    private String weatherStatus;
    private LocalDateTime weatherUpdatedAt;

    @ManyToMany
    @JoinTable(
            name = "place_managers",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> managers;

    @ManyToMany
    @JoinTable(
            name = "place_tag_map",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<PlaceTag> tags;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlaceImage> images;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlaceRating> ratings;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    // Nearby places
    @OneToMany(mappedBy = "fromPlace", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NearbyPlace> nearbyPlaces;

    // Comments (nếu muốn tách riêng từ PlaceRating)
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlaceComment> comments;
}
