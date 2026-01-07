package org.example.controller;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.PermissionRequest;
import org.example.dto.response.ApiResponse;
import org.example.dto.response.RoleResponse.PermissionResponse;
import org.example.service.permission.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/permission")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {
    PermissionService permissionService;


    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest){
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.create(permissionRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}
