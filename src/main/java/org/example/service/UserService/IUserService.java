package org.example.service.UserService;

import org.example.dto.request.UserRequest.UserCreationRequest;
import org.example.dto.request.UserRequest.UserUpdateRequest;
import org.example.dto.response.UserResponse.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserResponse createUser(UserCreationRequest userCreationRequest);

    UserResponse getMyInfo();

    UserResponse updateUser(UUID userId, UserUpdateRequest userUpdateRequest);

    void deleteUser(UUID id);

    List<UserResponse> getUsers();

    UserResponse getUser(UUID userId);


}
