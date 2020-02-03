package com.efostach.employeesmanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents Employee.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "salary")
    private int salary;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "department")
    private int department;
}