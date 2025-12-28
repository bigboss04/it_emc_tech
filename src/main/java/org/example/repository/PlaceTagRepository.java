package org.example.repository;

import org.example.model.PlaceTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {
}
