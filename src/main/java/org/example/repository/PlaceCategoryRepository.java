package org.example.repository;

import org.example.model.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndDeletedFalse(String name);
    List<PlaceCategory> findAllByDeletedFalse();
}
