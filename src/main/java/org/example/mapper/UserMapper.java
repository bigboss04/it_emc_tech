package org.example.mapper;

import org.example.dto.request.UserRequest.UserCreationRequest;
import org.example.dto.request.UserRequest.UserUpdateRequest;
import org.example.dto.response.UserResponse.UserResponse;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest creationRequest);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}