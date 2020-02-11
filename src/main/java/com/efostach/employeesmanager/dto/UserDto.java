package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Role;
import com.efostach.employeesmanager.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User DTO class for user requests
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private List<RoleDto> roles;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPhoneNumber(user.getPhoneNumber());

        List<RoleDto> userRoles = new ArrayList<>();

        for (Role role : user.getRoles()) {
            userRoles.add(RoleDto.fromRole(role));
        }
        userDto.setRoles(userRoles);

        return userDto;
    }
}