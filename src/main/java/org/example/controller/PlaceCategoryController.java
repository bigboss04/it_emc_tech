package org.example.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.PlaceCategoryRequest;
import org.example.dto.response.ApiResponse;
import org.example.dto.response.PlaceResponse.PlaceCategoryResponse;
import org.example.service.Place.PlaceCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.prefix}/place-category")
@RequiredArgsConstructor
public class PlaceCategoryController {
    private final PlaceCategoryService placeCategoryService;
    @PostMapping("")
    public ApiResponse<PlaceCategoryResponse> createPlace(@Valid @RequestBody PlaceCategoryRequest placeCategoryRequest ) {
        PlaceCategoryResponse placeCategoryResponse =  placeCategoryService.createPlaceCategory(placeCategoryRequest);
        return ApiResponse.<PlaceCategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(placeCategoryResponse)
                .message("Place category created successfully")
                .build();

    }

    @GetMapping("/all")
    public ApiResponse<List<PlaceCategoryResponse>> getAllPlaceCategories() {
        return ApiResponse.<List<PlaceCategoryResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .data(placeCategoryService.getAllPlaceCategories())
                .message("Place categories retrieved successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<PlaceCategoryResponse>> getPlaceCategoryById(@PathVariable Long id) {
        Optional<PlaceCategoryResponse> placeCategoryResponse = placeCategoryService.getPlaceCategoryById(id);
        return ApiResponse.<Optional<PlaceCategoryResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .data(placeCategoryResponse)
                .message("Place category retrieved successfully")
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PlaceCategoryResponse> updatePlaceCategory(
            @PathVariable Long id,
            @Valid @RequestBody PlaceCategoryRequest placeCategoryRequest) {
        PlaceCategoryResponse updatedPlaceCategory = placeCategoryService.updatePlaceCategory(id, placeCategoryRequest);
        return ApiResponse.<PlaceCategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(updatedPlaceCategory)
                .message("Place category updated successfully")
                .build();
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deletePlaceCategory(@PathVariable Long id) {
        placeCategoryService.deletePlaceCategory(id);
        return ApiResponse.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Place category deleted successfully")
                .build();
    }


}
