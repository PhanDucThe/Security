package com.ducthe.springsecurity.service;

import com.ducthe.springsecurity.model.request.AuthenticationRequest;
import com.ducthe.springsecurity.model.request.VerifyTokenRequest;
import com.ducthe.springsecurity.model.response.AuthencicationResponse;
import com.ducthe.springsecurity.model.response.VerifyTokenResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface UserService {

    AuthencicationResponse authenticate(AuthenticationRequest authenticationRequest);
    VerifyTokenResponse verifyToken(VerifyTokenRequest verifyTokenRequest) throws JOSEException, ParseException;
}
