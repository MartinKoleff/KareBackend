package com.koleff.kare.auth.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(

        @JsonProperty("user_id")
        String id,

        @JsonProperty("username")
        String username,

        @JsonProperty("password")
        String password,

        @JsonProperty("email")
        String email
) {
}
