package org.example.service.Place;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.PlaceCategoryRequest;
import org.example.dto.response.PlaceCategoryResponse;
import org.example.mapper.PlaceMapper;
import org.example.model.PlaceCategory;
import org.example.repository.PlaceCategoryRepository;
import org.example.repository.PlaceRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IPlaceServiceImpl implements IPlaceService{
    private final PlaceCategoryRepository placeCategoryRepository;
    private final PlaceMapper placeCategoryMapper;

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
            throw new DuplicateRequestException("Category name already exists",
                    ("Place category with name " + placeCategoryRequest.getName() + " already exists.");
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
        return List.of();
    }

    @Override
    public Optional<PlaceCategoryResponse> getPlaceCategoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public PlaceCategoryResponse updatePlaceCategory(Long id, PlaceCategory placeCategory) {
        return null;
    }

    @Override
    public void deletePlaceCategory(Long id) {

    }
}
