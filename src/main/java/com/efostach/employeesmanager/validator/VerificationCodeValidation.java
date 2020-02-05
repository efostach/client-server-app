package com.efostach.employeesmanager.validator;

import com.efostach.employeesmanager.model.User;
import com.efostach.employeesmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VerificationCodeValidation implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verificationCode", "Required");
        if (user.getVerificationCode().length() < 4 || user.getVerificationCode().length() > 4) {
            errors.rejectValue("verificationCode", "Size.userForm.verificationcode");
        }
    }
}
