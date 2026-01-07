package org.example.dto.response.UserResponse;


import lombok.*;
import org.example.dto.response.RoleResponse.RoleResponse;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    LocalDate dob;
    Set<String> roles;
}
