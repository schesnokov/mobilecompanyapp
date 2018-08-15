package com.mobilecompany.validators;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.text.DecimalFormat;

@Component
public class MainValidatorImpl implements MainValidator{

    @Override
    public void validateEmail(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.emailPattern(userDto.getEmail())) {
            errors.rejectValue("email", "Invalid email");
        }
    }

    @Override
    public void validatePassport(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.passportPattern(userDto.getPassportNumber())) {
            errors.rejectValue("passportNumber", "Invalid passport number");
        }
    }

    @Override
    public void validateDateOfBirth(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.datePattern(userDto.getDateOfBirth().toString())) {
            errors.rejectValue("dateOfBirth", "Invalid date");
        }
    }

    @Override
    public void validatePwSet(UserDto userDto, Errors errors) {
        if(!ValidatorPatterns.parolePattern(userDto.getPassword())) {
            errors.rejectValue("password", "Invalid password");
        }
    }

    @Override
    public void validatePhone(ContractDto contractDto, Errors errors) {
        if(!ValidatorPatterns.phonenumberPattern(contractDto.getNumber())) {
            errors.rejectValue("phone", "Invalid phone number");
        }
    }

    @Override
    public void validateTariffOrOption(TariffDto tariffDto, Errors errors) {
        if(!ValidatorPatterns.tariffOrOptionNamePattern(tariffDto.getTariffName())) {
            errors.rejectValue("tariffName", "Invalid tariff name");
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        String price = df.format(tariffDto.getTariffPrice());
        if(!ValidatorPatterns.pricePattern(price)) {
            errors.rejectValue("tariffPrice", "Invalid tariff price");
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
