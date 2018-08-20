package com.mobilecompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Tariff dto.
 */
public class TariffDto implements Serializable {

    private int id;
    private String tariffName;
    private BigDecimal tariffPrice;
    private String tariffDescription;
    private Set<OptionDto> availableOptions;

    /**
     * Instantiates a new Tariff dto.
     */
    public TariffDto() {
    }

    /**
     * Instantiates a new Tariff dto.
     *
     * @param id                the id
     * @param tariffName        the tariff name
     * @param tariffPrice       the tariff price
     * @param tariffDescription the tariff description
     */
    public TariffDto(int id, String tariffName, BigDecimal tariffPrice, String tariffDescription) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffPrice = tariffPrice;
        this.tariffDescription = tariffDescription;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets tariff name.
     *
     * @return the tariff name
     */
    public String getTariffName() {
        return tariffName;
    }

    /**
     * Sets tariff name.
     *
     * @param tariffName the tariff name
     */
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }


    /**
     * Gets tariff price.
     *
     * @return the tariff price
     */
    public BigDecimal getTariffPrice() {
        return tariffPrice;
    }

    /**
     * Sets tariff price.
     *
     * @param tariffPrice the tariff price
     */
    public void setTariffPrice(BigDecimal tariffPrice) {
        this.tariffPrice = tariffPrice;
    }


    /**
     * Gets tariff description.
     *
     * @return the tariff description
     */
    public String getTariffDescription() {
        return tariffDescription;
    }

    /**
     * Sets tariff description.
     *
     * @param tariffDescription the tariff description
     */
    public void setTariffDescription(String tariffDescription) {
        this.tariffDescription = tariffDescription;
    }

    /**
     * Gets available options.
     *
     * @return the available options
     */
    public Set<OptionDto> getAvailableOptions() {
        return availableOptions;
    }

    /**
     * Sets available options.
     *
     * @param availableOptions the available options
     */
    public void setAvailableOptions(Set<OptionDto> availableOptions) {
        this.availableOptions = availableOptions;
    }
}
