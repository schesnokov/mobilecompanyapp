package com.mobilecompany.controllers.model;

import java.util.List;

public class ContractChanges {

    private Integer tariffId;
    private List<Integer> optionsIds;

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public List<Integer> getOptionsIds() {
        return optionsIds;
    }

    public void setOptionsIds(List<Integer> optionsIds) {
        this.optionsIds = optionsIds;
    }
}
