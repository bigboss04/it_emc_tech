package org.example.repository;

import org.example.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
}
