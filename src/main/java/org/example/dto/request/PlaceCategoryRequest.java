package org.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceCategoryRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @NotBlank(message = "Icon is required")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "Icon must be a valid URL"
    )
    private String icon;
}
