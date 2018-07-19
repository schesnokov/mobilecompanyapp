package com.mobilecompany.services.api;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Options;

import java.util.List;

public interface OptionService {
    OptionDto getEntity(Integer id);
    List<Options> getAllOptions();
    void addOption(Options option);
}
