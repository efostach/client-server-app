package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.User;
import lombok.Data;

/**
 * DTO class for authentication (login) request
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
public class AuthenticationRequestDto {
    private String id;
    private String username;
    private String password;
    private String phoneNumber;
    private String verificationCode;

    public static AuthenticationRequestDto fromUser(User user) {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setId(String.valueOf(user.getId()));

        return authenticationRequestDto;
    }
}