package com.koleff.kare.service;

import com.koleff.kare.models.dto.AuthenticationResponse;
import com.koleff.kare.models.entity.User;

public interface AuthenticationService {

    User registerUser(String username, String password, String email);

    AuthenticationResponse loginUser(String username, String password);

    AuthenticationResponse refreshToken(String refreshToken);
}
