package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.AuthenticationRequest.AuthenticationRequest;
import org.example.dto.response.ApiResponse;
import org.example.dto.response.AuthenticationResponse.AuthenticationResponse;
import org.example.service.AuthenticationService.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .statusCode(200)
                .message("Authentication successful")
                .data(result)
                .build();
    }
}
