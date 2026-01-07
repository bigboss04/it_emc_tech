package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.RoleName;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName name;

    @ManyToMany
    Set<Permission> permissions;
}
