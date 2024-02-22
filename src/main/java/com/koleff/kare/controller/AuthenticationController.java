package com.koleff.kare.controller;

import com.koleff.kare.models.dto.LoginResponseDTO;
import com.koleff.kare.models.dto.RegistrationDTO;
import com.koleff.kare.models.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {

     User registerUser(@RequestBody RegistrationDTO body);

     LoginResponseDTO loginUser(@RequestBody RegistrationDTO body);

     //TODO: logoutUser...

     //TODO: refresh token...
}
