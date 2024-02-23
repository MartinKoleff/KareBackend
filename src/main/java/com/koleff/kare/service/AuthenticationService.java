package com.koleff.kare.service;

import com.koleff.kare.models.dto.LoginResponseDTO;
import com.koleff.kare.models.entity.User;

public interface AuthenticationService {

    User registerUser(String username, String password, String email);

    LoginResponseDTO loginUser(String username, String password);
}
