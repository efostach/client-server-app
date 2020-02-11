package com.efostach.employeesmanager.rest;

import com.efostach.employeesmanager.dto.AuthenticationRequestDto;
import com.efostach.employeesmanager.model.Status;
import com.efostach.employeesmanager.model.User;
import com.efostach.employeesmanager.security.jwt.JwtTokenProvider;
import com.efostach.employeesmanager.service.UserService;
import com.efostach.employeesmanager.service.twilio.TwilioSmsVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Helen Fostach
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final TwilioSmsVerificationService twilioSmsVerificationService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, TwilioSmsVerificationService twilioSmsVerificationService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.twilioSmsVerificationService = twilioSmsVerificationService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            } else {
                if (!user.getStatus().equals(Status.ACTIVE)) {
                    return new ResponseEntity("User with username: " + username + " is not active", HttpStatus.BAD_REQUEST);
                }
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        if (jwtTokenProvider.resolveToken(request) == null) {
            return new ResponseEntity<>("Session was completed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationRequestDto> register(@RequestBody AuthenticationRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String phoneNumber = requestDto.getPhoneNumber();

        User user = userService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity("User with username: " + username + " already exists", HttpStatus.BAD_REQUEST);
        } else {
            User newUser = new User();

            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setPhoneNumber(phoneNumber);

            AuthenticationRequestDto result = AuthenticationRequestDto.fromUser(userService.register(newUser));

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("verification")
    public ResponseEntity<String> registerConfirmation(@RequestBody AuthenticationRequestDto requestDto) {

        Long id = Long.valueOf(requestDto.getId());
        String code = requestDto.getVerificationCode();

        if (userService.confirm(id, code) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>("Verification code: " + code + " is incorrect", HttpStatus.BAD_REQUEST);
    }
}