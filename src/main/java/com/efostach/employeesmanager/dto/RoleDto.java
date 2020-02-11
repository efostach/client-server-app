package com.efostach.employeesmanager.dto;

import com.efostach.employeesmanager.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Role DTO class for user requests
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
    private Long id;
    private String name;

    public Role toRole()
    {
        Role role = new Role();
        role.setId(id);
        role.setName(name);

        return role;
    }

    public static RoleDto fromRole(Role role) {
        RoleDto rolesDto = new RoleDto();
        rolesDto.setName(role.getName());

        return rolesDto;
    }
}
