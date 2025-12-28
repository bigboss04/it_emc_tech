package org.example.dto.response.PlaceResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceCategoryResponse {
    private UUID id;
    private String name;
    private String icon;
}
