package org.example.service.permission;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.PermissionRequest;
import org.example.dto.response.RoleResponse.PermissionResponse;
import org.example.mapper.PermissionMapper;
import org.example.model.Permission;
import org.example.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService implements IPermissionService{
    private final ModelMapper modelMapper;
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;




    @Override
    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);

    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
       return  permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }


}
