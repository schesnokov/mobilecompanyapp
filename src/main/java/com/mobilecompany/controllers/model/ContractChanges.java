package com.mobilecompany.controllers.model;

import java.util.List;

public class ContractChanges {

    private Integer tariffId;
    private List<Integer> optionsIds1;
    private List<Integer> optionsIds2;

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public List<Integer> getOptionsIds1() {
        return optionsIds1;
    }

    public void setOptionsIds1(List<Integer> optionsIds1) {
        this.optionsIds1 = optionsIds1;
    }

    public List<Integer> getOptionsIds2() {
        return optionsIds2;
    }

    public void setOptionsIds2(List<Integer> optionsIds2) {
        this.optionsIds2 = optionsIds2;
    }
}
