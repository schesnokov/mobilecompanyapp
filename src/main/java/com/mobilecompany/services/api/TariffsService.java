package com.mobilecompany.services.api;

import com.mobilecompany.dto.TariffDto;

public interface TariffsService {
    TariffDto getEntity(Integer id);
    String getAllTariffs();
}
