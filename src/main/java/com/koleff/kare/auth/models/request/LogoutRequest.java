package com.koleff.kare.auth.models.request;

import com.koleff.kare.auth.models.dto.UserDto;
import com.koleff.kare.auth.models.entity.User;

public record LogoutRequest(UserDto user) {
}
