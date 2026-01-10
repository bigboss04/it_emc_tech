package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRequest {
    private String name;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private String openingHours;
    private String status; // Optional: "OPEN", "CLOSED"...

    private UUID provinceId;
    private Long categoryId; // id của PlaceCategory
    private Set<Long> tagIds; // id của PlaceTag

    // Tickets
    private Double basePrice;
    private List<TicketInfoRequest> ticketInfos;

    // Optional: status enum cho crowd/weather
    private String crowdStatus; // LOW/MEDIUM/HIGH/UNKNOWN
    private String weatherStatus; // SUNNY/RAINY/CLOUDY/STORM/UNKNOW
    private List<String> imageUrls;
}
