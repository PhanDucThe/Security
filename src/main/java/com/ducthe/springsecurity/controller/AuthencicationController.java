package com.ducthe.springsecurity.controller;

import com.ducthe.springsecurity.model.request.AuthenticationRequest;
import com.ducthe.springsecurity.model.request.VerifyTokenRequest;
import com.ducthe.springsecurity.model.response.ResponseData;
import com.ducthe.springsecurity.service.UserService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/auth")
public class AuthencicationController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/log-in")
    public ResponseData<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest)  {
        // Sau khi mình Tạo ra một token thì mình phải verify token có đúng hay không.

        return new ResponseData<>(HttpStatus.OK.value(), "Yen Nhi", userService.authenticate(authenticationRequest));
    }

    @PostMapping(value = "/verify")
    public ResponseData<?> isVerifyToken(@RequestBody VerifyTokenRequest verifyTokenRequest) throws ParseException, JOSEException {
        return new ResponseData<>(HttpStatus.OK.value(), "Yen Nhi", userService.verifyToken(verifyTokenRequest));
    }
}
