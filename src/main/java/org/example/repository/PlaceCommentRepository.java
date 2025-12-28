package org.example.repository;

import org.example.model.PlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCommentRepository extends JpaRepository<PlaceComment, Long> {
}
