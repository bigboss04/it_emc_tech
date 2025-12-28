package org.example.service.Place;

import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.PlaceCategoryRequest;
import org.example.dto.response.PlaceResponse.PlaceCategoryResponse;
import org.example.exception.ErrorCode;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.PlaceCategoryMapper;
import org.example.model.PlaceCategory;
import org.example.repository.PlaceCategoryRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceCategoryService implements IPlaceCategoryService {
    private final PlaceCategoryRepository placeCategoryRepository;
    private final PlaceCategoryMapper placeCategoryMapper;

    @Transactional
    @Override
    public PlaceCategoryResponse createPlaceCategory(PlaceCategoryRequest placeCategoryRequest) {
        validatePlaceCategory(placeCategoryRequest);
        PlaceCategory placeCategory = PlaceCategory.builder()
                .name(placeCategoryRequest.getName())
                .icon(placeCategoryRequest.getIcon())
                .build();
        PlaceCategory savedPlaceCategory = persistPlaceCategory(placeCategory);
        return placeCategoryMapper.toResponse(savedPlaceCategory);
    }

    private void validatePlaceCategory(PlaceCategoryRequest placeCategoryRequest) {
        if(placeCategoryRepository.existsByName(placeCategoryRequest.getName())) {
            throw new DuplicateRequestException(
                    "Place category with name '" + placeCategoryRequest.getName() + "' already exists."
            );
        }
    }

    private PlaceCategory persistPlaceCategory(PlaceCategory placeCategory) {
        try {
            PlaceCategory savedPlaceCategory = placeCategoryRepository.save(placeCategory);
            log.info("Place category saved successfully: {}", savedPlaceCategory);
            return savedPlaceCategory;
        } catch (Exception e) {
            log.error("Error saving place category: {}", e.getMessage());
            throw new ServiceException("CREATE_PLACE_CATEGORY_FAILED");
        }
    }



    @Override
    public List<PlaceCategoryResponse> getAllPlaceCategories() {
        return placeCategoryRepository.findAll()
                .stream()
                .map(placeCategoryMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PlaceCategoryResponse> getPlaceCategoryById(Long id) {
        return placeCategoryRepository.findById(id)
                .map(placeCategoryMapper::toResponse);
    }


    @Transactional
    @Override
    public PlaceCategoryResponse updatePlaceCategory(Long id, PlaceCategoryRequest placeCategoryRequest) {
        PlaceCategory existingPlaceCategory = placeCategoryRepository.findById(id)
                        .filter(placeCategory -> !placeCategory.isDeleted())
                        .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PLACE_CATEGORY));

        if(!existingPlaceCategory.getName().equals(placeCategoryRequest.getName())
                && placeCategoryRepository.existsByNameAndDeletedFalse(placeCategoryRequest.getName())) {
            throw new DuplicateRequestException(
                    "Place category with name '" + placeCategoryRequest.getName() + "' already exists."
            );
        }
        existingPlaceCategory.setName(placeCategoryRequest.getName());
        existingPlaceCategory.setIcon(placeCategoryRequest.getIcon());
        PlaceCategory updatedPlaceCategory = persistPlaceCategory(existingPlaceCategory);
        return placeCategoryMapper.toResponse(updatedPlaceCategory);
    }

    @Override
    public void deletePlaceCategory(Long id) {

        PlaceCategory placeCategory = placeCategoryRepository.findById(id)
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.PLACE_CATEGORY));
        placeCategory.setDeleted(true);
        try {
            placeCategoryRepository.save(placeCategory);
            log.info("Place category deleted successfully: ID={}", id);
        } catch (Exception e) {
            log.error("Error deleting place category: {}", e.getMessage());
            throw new ServiceException("DELETE_PLACE_CATEGORY_FAILED");
        }
    }
}
