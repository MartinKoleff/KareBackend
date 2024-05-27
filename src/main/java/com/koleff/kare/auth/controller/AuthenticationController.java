package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.models.request.AuthenticationRequest;
import com.koleff.kare.auth.models.request.LogoutRequest;
import com.koleff.kare.auth.models.response.AuthenticationResponse;
import com.koleff.kare.auth.models.response.RegistrationResponse;
import com.koleff.kare.common.base_response.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthenticationController {

    RegistrationResponse registerUser(@RequestBody AuthenticationRequest request);

    AuthenticationResponse loginUser(@RequestBody AuthenticationRequest request);

    AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken);

    BaseResponse logout(@RequestBody LogoutRequest request);

}
