package org.example.service.AuthenticationService;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.AuthenticationRequest.AuthenticationRequest;
import org.example.dto.response.AuthenticationResponse.AuthenticationResponse;
import org.example.exception.ErrorCode;
import org.example.exception.InvalidDataException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3NjY5MDUwODMsImV4cCI6MTc2NjkwODY4MywianRpIjoiYTIzMzIyNTEtMzQ0NC00ZjVjLTk0MGYtMGUzNGZiZmRlNmIyIiwiaXNzIjoiYXBpLmV4YW1wbGUuY29tIiwic3ViIjoidXNlcl8zNzAzIiwiYXVkIjoiaHR0cHM6Ly9leGFtcGxlLmNvbSJ9.QolFPv_wDD7Ht2RG_vAoeQsOKQazAmIm9Lh69E9rDaubObr7IbhMSr-Hvj3Ec9i1vj_pFiTg905qZLD9JEqxNg";

//    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
//        // Dummy implementation for authentication
//        // In a real implementation, you would verify the username and password here
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        User user = userRepository.findByUserName(authenticationRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
//
//        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
//
//        if(!authenticated ){
//            throw new InvalidDataException("Invalid credentials");
//        }

//        var token = generateToken(authenticationRequest.getUsername());

//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        var user = userRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(
                ()-> new ResourceNotFoundException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(),
                user.getPassword());
        if(!authenticated ){
            throw new ResourceNotFoundException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(authenticationRequest.getUserName());
        return  AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(String  userName) {
        // Dummy token generation
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("vandai.dev.vn")
                .issueTime(new java.util.Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
//        ki token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("JWT signing failed", e);
            throw new RuntimeException(e);
        }
    }
}
