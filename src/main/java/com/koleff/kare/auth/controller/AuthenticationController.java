package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.models.request.AuthenticationRequest;
import com.koleff.kare.auth.models.request.LogoutRequest;
import com.koleff.kare.auth.models.response.AuthenticationResponse;
import com.koleff.kare.auth.models.response.RegistrationResponse;
import com.koleff.kare.common.base_response.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthenticationController {
    AuthenticationResponse login(@RequestBody AuthenticationRequest request);
    RegistrationResponse register(@RequestBody AuthenticationRequest request);
    BaseResponse logout(@RequestBody LogoutRequest request);
    AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken);
}
