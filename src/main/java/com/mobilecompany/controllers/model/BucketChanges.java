package com.mobilecompany.controllers.model;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;

import java.util.Set;

public class BucketChanges {

    private Integer contractId;
    private TariffDto tariff;
    private Set<OptionDto> options;

    public BucketChanges() {
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public TariffDto getTariff() {
        return tariff;
    }

    public void setTariff(TariffDto tariff) {
        this.tariff = tariff;
    }

    public Set<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionDto> options) {
        this.options = options;
    }
}
