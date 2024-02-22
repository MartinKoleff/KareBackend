package com.koleff.kare.models.dto;

public record RegistrationDTO(
        String username,
        String password,
        String email
) {
    @Override
    public String toString() {
        return String.format("Registration info: Username: %s, Password: %s, Email: %s", this.username, this.password, this.email);
    }
}
