package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.NearbyType;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbyUpsertRequest {
    UUID id; // null => create, not null => update
    UUID fromPlaceId;
    UUID toPlaceId;
    UUID fromLocationId;
    UUID toLocationId;
    Double distanceKm;
    NearbyType type;
}
