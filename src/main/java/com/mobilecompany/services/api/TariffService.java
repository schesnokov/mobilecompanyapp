package com.mobilecompany.services.api;

import com.mobilecompany.entities.Tariff;

import java.util.List;

public interface TariffService {
    Tariff getTariff(Integer id);
    List<Tariff> getAllTariffs();
    void deleteTariff(Integer id);
    void addTariff(Tariff tariff);
}
