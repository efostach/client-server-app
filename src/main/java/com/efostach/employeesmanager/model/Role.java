package com.efostach.employeesmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents Role.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;
}