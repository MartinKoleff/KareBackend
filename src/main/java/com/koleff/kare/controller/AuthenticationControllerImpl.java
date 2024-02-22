package com.koleff.kare.controller;

import com.koleff.kare.models.dto.LoginResponseDTO;
import com.koleff.kare.models.dto.RegistrationDTO;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationControllerImpl implements AuthenticationController {

	private final AuthenticationService authenticationService;

    @Autowired
	public AuthenticationControllerImpl(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody RegistrationDTO body){
		return authenticationService.registerUser(body.username(), body.password(), body.email());
	}

	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
		return authenticationService.loginUser(body.username(), body.password());
	}
}
