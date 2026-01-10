package org.example.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearbyItemResponse {
    PlaceSummaryResponse place;
    Double distanceKm;
    String type;
}
