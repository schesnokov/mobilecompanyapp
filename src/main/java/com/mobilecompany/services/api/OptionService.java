package com.mobilecompany.services.api;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Option;

import java.util.List;

public interface OptionService {
    OptionDto getEntity(Integer id);
    List<Option> getAllOptions();
    void addOption(Option option);
}
