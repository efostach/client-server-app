package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO class for registration a new User request.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDto {
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String verificationCode;


}
