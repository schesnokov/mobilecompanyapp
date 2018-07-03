package com.mobilecompany.services.api;

import com.mobilecompany.dto.OptionDto;

public interface OptionService {
    OptionDto getEntity(Integer id);
    String getAllOptions();
}
