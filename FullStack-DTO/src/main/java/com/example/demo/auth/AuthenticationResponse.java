package com.example.demo.auth;

import education.nefel.nefelproject.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    /**
     * Represents the response returned after successful authentication.
     * Contains a JWT token and the user's role, fullName and email.
     */
    private String token;
    private String fullName;
    private String email;
    private Role role;
}
