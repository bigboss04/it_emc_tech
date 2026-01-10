package org.example.service.permission;

import org.example.dto.request.PermissionRequest;
import org.example.dto.response.RoleResponse.PermissionResponse;

import java.util.List;


public interface IPermissionService {
    PermissionResponse create(PermissionRequest permissionRequest);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
