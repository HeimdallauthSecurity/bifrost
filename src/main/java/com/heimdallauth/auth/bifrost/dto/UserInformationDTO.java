package com.heimdallauth.auth.bifrost.dto;

public record UserInformationDTO(
        String id,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
