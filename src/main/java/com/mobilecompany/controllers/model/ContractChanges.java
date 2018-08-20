package com.mobilecompany.controllers.model;

import java.util.List;

/**
 * The type Contract changes.
 */
public class ContractChanges {

    private Integer tariffId;
    private List<Integer> optionsIds1;
    private List<Integer> optionsIds2;

    /**
     * Gets tariff id.
     *
     * @return the tariff id
     */
    public Integer getTariffId() {
        return tariffId;
    }

    /**
     * Sets tariff id.
     *
     * @param tariffId the tariff id
     */
    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    /**
     * Gets options ids 1.
     *
     * @return the options ids 1
     */
    public List<Integer> getOptionsIds1() {
        return optionsIds1;
    }

    /**
     * Sets options ids 1.
     *
     * @param optionsIds1 the options ids 1
     */
    public void setOptionsIds1(List<Integer> optionsIds1) {
        this.optionsIds1 = optionsIds1;
    }

    /**
     * Gets options ids 2.
     *
     * @return the options ids 2
     */
    public List<Integer> getOptionsIds2() {
        return optionsIds2;
    }

    /**
     * Sets options ids 2.
     *
     * @param optionsIds2 the options ids 2
     */
    public void setOptionsIds2(List<Integer> optionsIds2) {
        this.optionsIds2 = optionsIds2;
    }
}
