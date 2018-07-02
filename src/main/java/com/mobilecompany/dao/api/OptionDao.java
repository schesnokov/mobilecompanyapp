package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Option;

public interface OptionDao {
    void create(Option option);
    Option read(Integer id);
    void update(Integer id);
    void delete(Integer id);
}
