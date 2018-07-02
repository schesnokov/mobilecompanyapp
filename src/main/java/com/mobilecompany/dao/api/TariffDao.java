package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Tariffs;

import java.util.List;

public interface TariffDao {
    void create(Tariffs tariff);
    Tariffs read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Tariffs> findAllTariffs();
}
