package org.example.service.Place;


import org.example.dto.request.PlaceCategoryRequest;
import org.example.dto.response.PlaceResponse.PlaceCategoryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IPlaceCategoryService {
    PlaceCategoryResponse createPlaceCategory(PlaceCategoryRequest placeCategoryRequest);
    List<PlaceCategoryResponse> getAllPlaceCategories();
    Optional<PlaceCategoryResponse> getPlaceCategoryById(Long id);
    PlaceCategoryResponse updatePlaceCategory(Long id, PlaceCategoryRequest placeCategoryRequest);
    void deletePlaceCategory(Long id);
}
