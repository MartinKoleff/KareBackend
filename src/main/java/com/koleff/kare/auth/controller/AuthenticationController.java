package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.models.response.AuthenticationResponse;
import com.koleff.kare.auth.models.dto.RegistrationDTO;
import com.koleff.kare.auth.models.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthenticationController {

     User registerUser(@RequestBody RegistrationDTO body);

     AuthenticationResponse loginUser(@RequestBody RegistrationDTO body);

     AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken);

     //TODO: logoutUser...
}
