package com.mobilecompany.services.impl;

import com.mobilecompany.services.api.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * The Security service.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    /**
     * Instantiates a new Security service.
     *
     * @param authenticationManager the authentication manager
     * @param userDetailsService    the user details service
     */
    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Find logged in email string.
     *
     * @return the email
     */
    @Override
    public String findLoggedInEmail() {
        LOGGER.info("Finding logged in user");
        Object userDetails = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (userDetails instanceof UserDetails)
            return ((UserDetails) userDetails).getUsername();

        return null;
    }

    /**
     * Auto login.
     *
     * @param email    the email
     * @param parole the password
     */
    @Override
    public void autoLogin(String email, String parole) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, parole, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}