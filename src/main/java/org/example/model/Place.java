package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.PlaceStatus;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "places")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PlaceCategory category;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImage> images;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceRating> ratings;

    @ManyToMany
    @JoinTable(
            name = "place_tag_map",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<PlaceTag> tags;
}
