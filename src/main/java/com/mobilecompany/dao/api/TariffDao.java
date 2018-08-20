package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Tariff;

import java.util.List;

/**
 * The interface Tariff dao.
 */
public interface TariffDao {
    /**
     * Create.
     *
     * @param tariff the tariff
     */
    void create(Tariff tariff);

    /**
     * Read tariff.
     *
     * @param id the id
     * @return the tariff
     */
    Tariff read(Integer id);

    /**
     * Update.
     *
     * @param tariff the tariff
     */
    void update(Tariff tariff);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Find all tariffs list.
     *
     * @return the list of tariffs
     */
    List<Tariff> findAllTariffs();
}
