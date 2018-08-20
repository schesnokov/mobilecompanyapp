package com.mobilecompany.controllers.model;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;

import java.util.Set;


/**
 * The type Bucket changes.
 */
public class BucketChanges {

    private Integer contractId;
    private TariffDto tariff;
    private Set<OptionDto> options;

    /**
     * Instantiates a new Bucket changes.
     */
    public BucketChanges() {
    }

    /**
     * Gets contract id.
     *
     * @return the contract id
     */
    public Integer getContractId() {
        return contractId;
    }

    /**
     * Sets contract id.
     *
     * @param contractId the contract id
     */
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    /**
     * Gets tariff.
     *
     * @return the tariff
     */
    public TariffDto getTariff() {
        return tariff;
    }

    /**
     * Sets tariff.
     *
     * @param tariff the tariff
     */
    public void setTariff(TariffDto tariff) {
        this.tariff = tariff;
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public Set<OptionDto> getOptions() {
        return options;
    }

    /**
     * Sets options.
     *
     * @param options the options
     */
    public void setOptions(Set<OptionDto> options) {
        this.options = options;
    }
}
