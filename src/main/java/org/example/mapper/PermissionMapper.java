package org.example.mapper;

import org.example.dto.request.PermissionRequest;
import org.example.dto.response.RoleResponse.PermissionResponse;
import org.example.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
