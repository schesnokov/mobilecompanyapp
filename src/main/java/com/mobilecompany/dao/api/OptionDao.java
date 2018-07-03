package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Options;

import java.util.List;

public interface OptionDao {
    void create(Options option);
    Options read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Options> findAllOptions();
}
