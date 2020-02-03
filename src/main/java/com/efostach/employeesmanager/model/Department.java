package com.efostach.employeesmanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents Department.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Entity
@Table(name = "departments")
@Getter
@Setter
@ToString
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;
}