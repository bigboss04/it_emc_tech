package org.example.repository;

import org.example.model.FavoritePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
}
