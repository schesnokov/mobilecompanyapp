package com.mobilecompany.services.api;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Options;

public interface OptionService {
    OptionDto getEntity(Integer id);
    String getAllOptions();
    void addOption(Options option);
}
