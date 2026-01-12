package org.example.dto.request.Province;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateNameRequest {
    private String name;
    private String icon;
}
