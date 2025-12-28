package org.example.dto.response.RoleResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<RoleResponse> roles;
}
