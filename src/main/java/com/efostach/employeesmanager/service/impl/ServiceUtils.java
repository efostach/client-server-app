package com.efostach.employeesmanager.service.impl;

import com.efostach.employeesmanager.model.User;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Common utilities class.
 *
 * @author Helen Fostach
 * @version 1.0
 */

public class ServiceUtils {

    public static Date getTimestampUTC() {
        return Date.from(Instant.now(Clock.systemUTC()));
    }
}
