package org.example.mapper;

import org.example.dto.request.PlaceCategoryRequest;
import org.example.dto.response.PlaceCategoryResponse;
import org.example.model.Place;
import org.example.model.PlaceCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place toEntity(PlaceCategoryRequest placeCategoryRequest);
    PlaceCategoryResponse toResponse(PlaceCategory placeCategory);
}
