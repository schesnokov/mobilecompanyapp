package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Option;

public interface OptionDao {
    void create(Option option);
    Option read(Integer a);
    void update(Option option);
    void delete(Integer id);
}
