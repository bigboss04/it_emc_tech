package org.example.service.AuthenticationService;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.AuthenticationRequest.AuthenticationRequest;
import org.example.dto.request.IntrospectTokenRequest.IntrospectTokenRequest;
import org.example.dto.response.AuthenticationResponse.AuthenticationResponse;
import org.example.dto.response.IntrospectResponse;
import org.example.exception.ErrorCode;
import org.example.exception.ResourceNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService{

    private final UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        var user = userRepository.findByUserName(authenticationRequest.getUserName()).orElseThrow(
                ()-> new ResourceNotFoundException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(),
                user.getPassword());
        if(!authenticated ){
            throw new ResourceNotFoundException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user);
        return  AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(User user) {
        // Dummy token generation
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("vandai.dev.vn")
                .issueTime(new java.util.Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(user))
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

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }


    @Override
    public IntrospectResponse introspect(IntrospectTokenRequest introspectTokenRequest) throws JOSEException, ParseException {
        var token = introspectTokenRequest.getToken();
        JWSVerifier  verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime =   signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified =  signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }
}
