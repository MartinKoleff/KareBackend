package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.models.dto.UserDto;
import com.koleff.kare.auth.models.request.AuthenticationRequest;
import com.koleff.kare.auth.models.request.LogoutRequest;
import com.koleff.kare.auth.models.response.AuthenticationResponse;
import com.koleff.kare.auth.models.response.RegistrationResponse;
import com.koleff.kare.auth.service.AuthenticationServiceImpl;
import com.koleff.kare.common.base_response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthenticationControllerImpl(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegistrationResponse registerUser(@RequestBody AuthenticationRequest request) {
        UserDto user = authenticationService.registerUser(request.username(), request.password(), request.email());

        return new RegistrationResponse(user);
    }

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody AuthenticationRequest request) {
        return authenticationService.loginUser(request.username(), request.password());
    }

    @PostMapping("/refreshtoken")
    public AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return authenticationService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public BaseResponse logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request.user());

        return new BaseResponse(
                true,
                null
        );
    }
}
