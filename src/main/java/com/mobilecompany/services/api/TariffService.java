package com.mobilecompany.services.api;

import com.mobilecompany.dto.TariffDto;

import java.util.List;

public interface TariffService {
    TariffDto getTariff(Integer id);

    List<TariffDto> getAllTariffs();

    void deleteTariff(Integer id);

    void addTariff(TariffDto tariff);

    void sendUpdateMessageToJmsServer();

    void update(TariffDto tariff);
}
