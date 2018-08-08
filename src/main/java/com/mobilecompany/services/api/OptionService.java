package com.mobilecompany.services.api;

import com.mobilecompany.dto.OptionDto;

import java.util.List;

public interface OptionService {
    OptionDto getOption(Integer id);

    List<OptionDto> getAllOptions();

    void addOption(OptionDto option);
}
