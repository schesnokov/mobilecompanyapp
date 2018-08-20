package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Option;

import java.util.List;

/**
 * The interface Option dao.
 */
public interface OptionDao {
    /**
     * Create.
     *
     * @param option the option
     */
    void create(Option option);

    /**
     * Read option.
     *
     * @param id the id
     * @return the option
     */
    Option read(Integer id);

    /**
     * Update.
     *
     * @param option the option
     */
    void update(Option option);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Find all options list.
     *
     * @return the list of options
     */
    List<Option> findAllOptions();
}
