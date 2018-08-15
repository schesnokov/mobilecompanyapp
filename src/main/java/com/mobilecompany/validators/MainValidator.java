package com.mobilecompany.validators;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface MainValidator extends Validator {
    void validateEmail(UserDto userDto, Errors errors);
    void validatePassport(UserDto userDto, Errors errors);
    void validateDateOfBirth(UserDto userDto, Errors errors);
    void validatePwSet(UserDto userDto, Errors errors);
    void validatePhone(ContractDto contractDto, Errors errors);
    void validateTariffOrOption(TariffDto tariffDto, Errors errors);
}
