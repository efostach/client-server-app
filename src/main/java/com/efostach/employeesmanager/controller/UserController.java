package com.efostach.employeesmanager.controller;

import com.efostach.employeesmanager.model.User;
import com.efostach.employeesmanager.service.SecurityService;
import com.efostach.employeesmanager.service.UserService;
import com.efostach.employeesmanager.twilio.TwilioSmsVerification;
import com.efostach.employeesmanager.validator.UserValidator;
import com.efostach.employeesmanager.validator.VerificationCodeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link User} connected requests.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private VerificationCodeValidation verificationCodeValidation;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute( "userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verification(Model model, User user) {
        model.addAttribute( "verificationForm", user);

        return "verification";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        TwilioSmsVerification a = new TwilioSmsVerification();
        userForm.setVerificationCode(a.sentMsg(userForm.getPhoneNumber()));

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/verification";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public String verification(@ModelAttribute("verificationForm")User verificationForm, User user, BindingResult bindingResult, Model model) {

        verificationCodeValidation.validate(verificationForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "verification";
        }

        if (!user.getVerificationCode().equals(verificationForm.getVerificationCode())) {
            model.addAttribute("error", "Verification code is wrong.");
            userService.remove(user);
            return "redirect:/registration";
        }

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }
}
