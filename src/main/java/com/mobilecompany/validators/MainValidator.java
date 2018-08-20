package com.mobilecompany.validators;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The interface Main validator.
 */
public interface MainValidator extends Validator {
    /**
     * Validate email.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    void validateEmail(UserDto userDto, Errors errors);

    /**
     * Validate passport.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    void validatePassport(UserDto userDto, Errors errors);

    /**
     * Validate date of birth.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    void validateDateOfBirth(UserDto userDto, Errors errors);

    /**
     * Validate pw set.
     *
     * @param userDto the user dto
     * @param errors  the errors
     */
    void validatePwSet(UserDto userDto, Errors errors);

    /**
     * Validate phone.
     *
     * @param contractDto the contract dto
     * @param errors      the errors
     */
    void validatePhone(ContractDto contractDto, Errors errors);
}
