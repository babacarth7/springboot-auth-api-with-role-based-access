package com.senegal_service_hub.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpUserDto {
    private String name;
    private String email;
    private String mobile;
    private String password;

    @Override
    public String toString() {
        return "SignUpUserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
