package com.koleff.kare.auth.models.request;

public record AuthenticationRequest(String username, String password, String email) {
}
