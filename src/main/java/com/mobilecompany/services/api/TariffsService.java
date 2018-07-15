package com.mobilecompany.services.api;

import com.mobilecompany.entities.Tariffs;

import java.util.List;

public interface TariffsService {
    Tariffs getEntity(Integer id);
    List<Tariffs> getAllTariffs();
    void deleteTariff(Integer id);
    void addTariff(Tariffs tariff);
}
