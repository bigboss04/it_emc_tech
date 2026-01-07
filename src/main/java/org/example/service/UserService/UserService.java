package org.example.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.UserRequest.UserCreationRequest;
import org.example.dto.request.UserRequest.UserUpdateRequest;
import org.example.dto.response.UserResponse.UserResponse;
import org.example.enums.RoleName;
import org.example.exception.ErrorCode;
import org.example.exception.InvalidDataException;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        if(userRepository.existsByUserName(userCreationRequest.getUserName()))
            throw new InvalidDataException(ErrorCode.USER_EXISTED.getMessage());

        User user = userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        Role role = roleRepository.findByName(RoleName.USER);
        user.setRoles(roles );
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getMyInfo() {
        String userName = Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getName();
          User user =  userRepository.findByUserName(userName).orElseThrow(
                  ()-> new ResourceNotFoundException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, userUpdateRequest);
        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
//        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponse> getUsers() {
        log.info("IN METHOD GET USERS");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse getUser(UUID userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException(ErrorCode.USER_EXISTED)));
    }
}
