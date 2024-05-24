package com.koleff.kare.auth.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koleff.kare.auth.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data class AuthenticationResponse {

    @JsonProperty("user")
    private User user;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
