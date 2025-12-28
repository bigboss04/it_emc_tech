package org.example.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketInfoRequest {
    private String ticketType;
    private String target;
    private String timeSlot;
    private String note;
    private Double price;
}
