package com.mobilecompany.validators;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.UserDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * The type Main validator.
 */
@Component
public class MainValidatorImpl implements MainValidator{

    /**
     * Validate email.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    @Override
    public void validateEmail(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.emailPattern(userDto.getEmail())) {
            errors.rejectValue("email", "Invalid email");
        }
    }

    /**
     * Validate passport.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    @Override
    public void validatePassport(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.passportPattern(userDto.getPassportNumber())) {
            errors.rejectValue("passportNumber", "Invalid passport number");
        }
    }

    /**
     * Validate date of birth.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    @Override
    public void validateDateOfBirth(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.datePattern(userDto.getDateOfBirth().toString())) {
            errors.rejectValue("dateOfBirth", "Invalid date");
        }
    }

    /**
     * Validate pw set.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    @Override
    public void validatePwSet(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.parolePattern(userDto.getPassword())) {
            errors.rejectValue("password", "Invalid password");
        }
    }

    /**
     * Validate phone.
     *
     * @param contractDto the contract dto
     * @param errors      the errors
     */
    @Override
    public void validatePhone(ContractDto contractDto, Errors errors) {
        if(!ValidatorPatterns.phonenumberPattern(contractDto.getNumber())) {
            errors.rejectValue("phone", "Invalid phone number");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

    }
}
