package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Tariff;

import java.util.List;

public interface TariffDao {
    void create(Tariff tariff);
    Tariff read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Tariff> findAllTariffs();
}
