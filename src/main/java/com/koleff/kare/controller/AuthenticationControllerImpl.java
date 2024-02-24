package com.koleff.kare.controller;

import com.koleff.kare.models.dto.AuthenticationResponse;
import com.koleff.kare.models.dto.RegistrationDTO;
import com.koleff.kare.models.entity.User;
import com.koleff.kare.service.AuthenticationServiceImpl;
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
	public User registerUser(@RequestBody RegistrationDTO body){
		return authenticationService.registerUser(body.username(), body.password(), body.email());
	}

	@PostMapping("/login")
	public AuthenticationResponse loginUser(@RequestBody RegistrationDTO body){
		return authenticationService.loginUser(body.username(), body.password());
	}

	@PostMapping("/refreshtoken")
	public AuthenticationResponse refreshToken(@RequestParam("refreshToken") String refreshToken) {
		return authenticationService.refreshToken(refreshToken);
	}
}
