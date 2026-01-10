package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceCreateRequest {
    String name;
    String description;
    String address;
    Double latitude;
    Double longitude;
    String openingHours;
    String status;
    UUID provinceId;
    UUID categoryId;
    Set<UUID> tagIds;
    String weatherStatus;
    String crowdStatus;
    List<String> imageUrls;
}
