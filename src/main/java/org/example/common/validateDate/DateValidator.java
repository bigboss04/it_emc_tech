package org.example.common.validateDate;

import org.springframework.http.HttpStatus;

public class validateDate {
    private void Datevalidate(java.time.LocalDate checkIn, java.time.LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "checkInDate/checkOutDate is required");
        }
        if (!checkIn.isBefore(checkOut)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "checkInDate must be before checkOutDate");
        }
    }
}
