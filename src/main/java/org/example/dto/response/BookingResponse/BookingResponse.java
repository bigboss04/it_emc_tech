package org.example.dto.response;


import lombok.Builder;
import lombok.Data;
import org.example.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder

public class BookingResponse {
    UUID id;
    UUID accommodationId;
    UUID roomTypeId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer rooms;
    Double pricePerNight;
    Double totalPrice;
    BookingStatus status;
    LocalDateTime createdAt;
}
