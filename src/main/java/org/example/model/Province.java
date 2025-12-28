package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "provinces")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")    private UUID id;

    private String name;
}
