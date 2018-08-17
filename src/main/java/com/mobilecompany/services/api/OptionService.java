package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;

import java.util.List;

public interface OptionService {
    OptionDto getOption(Integer id);

    List<OptionDto> getAllOptions();

    void addOption(NewOptionHelper newOption);

    void changeOptionStatus(Integer optionId);

    TariffDto addAvailableOptions(Integer tariffId, ContractChanges contractChanges);

    TariffDto deleteAvailableOptions(Integer tariffId, ContractChanges contractChanges);

    List<Integer> getConflictedOptions(Integer optionId);

    List<Integer> getDependentOptions(Integer optionId);
}
