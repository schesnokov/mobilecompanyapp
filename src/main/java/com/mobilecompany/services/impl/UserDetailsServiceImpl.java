package com.mobilecompany.services.impl;

import com.mobilecompany.dao.impl.UsersDaoImpl;
import com.mobilecompany.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDaoImpl usersDao;

    @Autowired
    public UserDetailsServiceImpl(UsersDaoImpl usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Users user = usersDao.getByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoles().getName()));

         return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
