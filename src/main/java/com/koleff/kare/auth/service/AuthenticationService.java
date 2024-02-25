package com.koleff.kare.auth.service;

import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.auth.models.dto.AuthenticationResponse;

public interface AuthenticationService {

    User registerUser(String username, String password, String email);

    AuthenticationResponse loginUser(String username, String password);

    AuthenticationResponse refreshToken(String refreshToken);
}
