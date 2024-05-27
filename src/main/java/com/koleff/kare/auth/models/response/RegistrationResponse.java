package com.koleff.kare.auth.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.auth.models.dto.UserDto;
import com.koleff.kare.auth.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data class RegistrationResponse {

    @JsonProperty("user")
    private UserDto user;
}
