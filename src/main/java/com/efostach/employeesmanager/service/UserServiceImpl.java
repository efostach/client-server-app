package com.efostach.employeesmanager.service;

import com.efostach.employeesmanager.dao.RoleDao;
import com.efostach.employeesmanager.dao.UserDao;
import com.efostach.employeesmanager.model.Role;
import com.efostach.employeesmanager.model.User;
import com.twilio.Twilio;
import com.twilio.rest.chat.v1.service.channel.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementations of {@link UserService} interface.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static Long ROLE_USER_BY_DEFAULT = 3L;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(ROLE_USER_BY_DEFAULT));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void remove(User user) {
        userDao.delete(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public void sendMsg(String phoneNumber) {


    }
}
