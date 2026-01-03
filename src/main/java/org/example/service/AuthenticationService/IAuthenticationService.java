package org.example.service.AuthenticationService;

import com.nimbusds.jose.JOSEException;
import org.example.dto.request.IntrospectTokenRequest.IntrospectTokenRequest;
import org.example.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectTokenRequest introspectTokenRequest) throws JOSEException, ParseException;
}
