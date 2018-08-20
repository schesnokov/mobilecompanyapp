package com.mobilecompany.services.api;

import com.mobilecompany.dto.TariffDto;

import java.util.List;

/**
 * The interface Tariff service.
 */
public interface TariffService {
    /**
     * Gets tariff.
     *
     * @param id the id
     * @return the tariff
     */
    TariffDto getTariff(Integer id);

    /**
     * Gets all tariffs.
     *
     * @return the all tariffs
     */
    List<TariffDto> getAllTariffs();

    /**
     * Delete tariff.
     *
     * @param id the id
     */
    void deleteTariff(Integer id);

    /**
     * Add tariff.
     *
     * @param tariff the tariff
     */
    void addTariff(TariffDto tariff);

    /**
     * Send update message to jms server.
     */
    void sendUpdateMessageToJmsServer();

    /**
     * Update.
     *
     * @param tariff the tariff
     */
    void update(TariffDto tariff);

    /**
     * Change tariff status.
     *
     * @param tariffId the tariff id
     */
    void changeTariffStatus(Integer tariffId);
}
