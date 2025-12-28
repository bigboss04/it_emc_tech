package org.example.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserRequest.UserCreationRequest;
import org.example.dto.response.ApiResponse;
import org.example.dto.response.UserResponse.UserResponse;
import org.example.service.UserService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.fasterxml.classmate.AnnotationOverrides.builder;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        System.out.println("DOB = " + userCreationRequest.getDob());
        return ApiResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(userService.createUser(userCreationRequest))
                .message("USER CREATE SUCCESSFULLY")
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable UUID userId){
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUser(userId))
                .message("GET USER SUCCESSFULLY")
                .build();
    }
//
//    @GetMapping("/my-info")
//    ApiResponse<UserResponse> getMyInfo(){
//        return ApiResponse.<UserResponse>builder()
//                .data(user)
//    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .message("User has been deleted")
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getUsers())
                .message("User Get List successfully")
                .build();
    }



}
