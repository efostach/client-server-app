package com.efostach.employeesmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString(exclude = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;
}