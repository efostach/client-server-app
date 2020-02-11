package com.efostach.employeesmanager.service.impl;

import com.efostach.employeesmanager.model.Role;
import com.efostach.employeesmanager.model.Status;
import com.efostach.employeesmanager.model.User;
import com.efostach.employeesmanager.repository.RoleRepository;
import com.efostach.employeesmanager.repository.UserRepository;
import com.efostach.employeesmanager.service.UserService;
import com.efostach.employeesmanager.service.twilio.TwilioSmsVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.efostach.employeesmanager.service.impl.ServiceUtils.getTimestampUTC;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} and business logic.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private TwilioSmsVerificationService twilioSmsVerificationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerificationCode(twilioSmsVerificationService.sentMsg(user.getPhoneNumber()));
        user.setRoles(userRoles);
        user.setCreated(getTimestampUTC());
        user.setUpdated(getTimestampUTC());
        user.setStatus(Status.NOT_ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public User confirm(Long id, String code) {
        User user = findById(id);
        if (user != null
                && user.getVerificationCode().equals(code)) {
            user.setStatus(Status.ACTIVE);
            user.setUpdated(getTimestampUTC());
            userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
