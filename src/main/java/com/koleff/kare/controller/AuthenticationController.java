package com.koleff.kare.controller;

import com.koleff.kare.models.dto.AuthenticationResponse;
import com.koleff.kare.models.dto.RegistrationDTO;
import com.koleff.kare.models.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthenticationController {

     User registerUser(@RequestBody RegistrationDTO body);

     AuthenticationResponse loginUser(@RequestBody RegistrationDTO body);

     AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken);

     //TODO: logoutUser...
}
