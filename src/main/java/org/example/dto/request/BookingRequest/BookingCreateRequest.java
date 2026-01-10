package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.AccommodationType;

import java.time.LocalDate;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BookingCreateRequest {
    UUID accommodationId;
    UUID roomTypeId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer rooms;
    String guestName;  // optional (nếu muốn)
    String guestPhone; // optional
}
