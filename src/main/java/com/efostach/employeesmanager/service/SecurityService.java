package com.efostach.employeesmanager.service;

/**
 * Service interface for {@link java.security.Security} class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}