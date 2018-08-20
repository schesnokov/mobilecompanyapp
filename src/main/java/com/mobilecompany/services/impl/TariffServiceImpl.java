package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.entities.Tariff;
import com.mobilecompany.services.api.TariffService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Tariff service.
 */
@Service
public class TariffServiceImpl implements TariffService {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffServiceImpl.class);

    private TariffDao tariffDao;
    private JmsTemplate jmsTemplate;
    private ModelMapper mapper;

    /**
     * Instantiates a new Tariff service.
     *
     * @param tariffDao   the tariff dao
     * @param jmsTemplate the jms template
     */
    @Autowired
    public TariffServiceImpl(TariffDao tariffDao, JmsTemplate jmsTemplate) {
        this.tariffDao = tariffDao;
        this.mapper = new ModelMapper();
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Gets tariff.
     *
     * @param id the id
     * @return the tariff
     */
    @Override
    @Transactional
    public TariffDto getTariff(Integer id) {
        LOGGER.info("Getting tariffDto from Entity with id {}", id);
        return mapper.map(tariffDao.read(id), TariffDto.class);
    }

    /**
     * Gets all tariffs.
     *
     * @return the all tariffs
     */
    @Override
    @Transactional
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("Getting list with tariffDtos with all tariffs");
        List<TariffDto> tariffDtoList = new ArrayList<>();
        for (Tariff tariff : tariffDao.findAllTariffs()) {
            tariffDtoList.add(mapper.map(tariff, TariffDto.class));
        }
        return tariffDtoList;
    }

    /**
     * Update.
     *
     * @param tariff the tariff
     */
    @Override
    @Transactional
    public void update(TariffDto tariff) {
        LOGGER.info("Passing tariffDto to DAO to update");
        tariffDao.update(mapper.map(tariff, Tariff.class));
    }

    /**
     * Delete tariff.
     *
     * @param id the id
     */
    @Override
    @Transactional
    public void deleteTariff(Integer id) {
        LOGGER.info("Passing tariff id {} for tariff delete", id);
        tariffDao.delete(id);
    }

    /**
     * Add tariff.
     *
     * @param tariff the tariff
     */
    @Override
    @Transactional
    public void addTariff(TariffDto tariff) {
        LOGGER.info("Passing new tariffDto to DAO for tariff creation");
        tariffDao.create(mapper.map(tariff, Tariff.class));
    }

    /**
     * Send update message to jms server.
     */
    @Override
    public void sendUpdateMessageToJmsServer() {
        sendMessage();
    }

    private void sendMessage() {
        jmsTemplate.send("advertising.stand", session -> {
            TextMessage msg = session.createTextMessage();
            msg.setText("update");
            return msg;
        });
    }

    /**
     * Change tariff status.
     *
     * @param tariffId the tariff id
     */
    @Override
    @Transactional
    public void changeTariffStatus(Integer tariffId) {
        LOGGER.info("Blocking tariff with id {}", tariffId);
        Tariff tariff = tariffDao.read(tariffId);
        tariff.setTariffIsBlocked(1);
        tariffDao.update(tariff);
    }
}
