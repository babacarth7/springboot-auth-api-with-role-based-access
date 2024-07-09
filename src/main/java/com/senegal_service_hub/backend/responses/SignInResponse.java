package com.senegal_service_hub.backend.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInResponse {
    private String token;
    private long expiresIn;

    @Override
    public String toString() {
        return "SignInResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn + '}';
    }
}
