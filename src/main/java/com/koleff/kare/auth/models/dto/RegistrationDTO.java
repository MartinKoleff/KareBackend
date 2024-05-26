package com.koleff.kare.auth.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegistrationDTO(

        @JsonProperty("username")
        String username,

        @JsonProperty("password")
        String password,

        @JsonProperty("email")
        String email
) {
    @Override
    public String toString() {
        return String.format("Registration info: Username: %s, Password: %s, Email: %s", this.username, this.password, this.email);
    }
}
