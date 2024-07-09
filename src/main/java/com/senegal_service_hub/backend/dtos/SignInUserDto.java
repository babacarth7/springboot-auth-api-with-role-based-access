package com.senegal_service_hub.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInUserDto {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "SignInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
