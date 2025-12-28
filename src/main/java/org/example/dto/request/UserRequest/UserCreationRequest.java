package org.example.dto.request.UserRequest;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.validator.DobConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;

    private String firstName;
    private String lastName;

    @DobConstraint(min = 10, message = "INVALID_DOB")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
