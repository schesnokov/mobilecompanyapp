package com.mobilecompany.services.impl;

import com.mobilecompany.dao.impl.UserDaoImpl;
import com.mobilecompany.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserDaoImpl usersDao;

    /**
     * Instantiates a new User details service.
     *
     * @param usersDao the users dao
     */
    @Autowired
    public UserDetailsServiceImpl(UserDaoImpl usersDao) {
        this.usersDao = usersDao;
    }

    /**
     * Loads User by email
     *
     * @param email the users email
     *
     * @return User by email
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        LOGGER.info("Getting user details by email {}", email);
        User user = usersDao.getByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
