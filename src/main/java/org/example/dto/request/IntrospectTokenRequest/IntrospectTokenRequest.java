package org.example.dto.request.IntrospectTokenRequest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntrospectTokenRequest {
    private String token;
}
