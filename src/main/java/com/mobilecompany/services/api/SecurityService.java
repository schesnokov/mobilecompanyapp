package com.mobilecompany.services.api;

/**
 * The interface Security service.
 */
public interface SecurityService {
    /**
     * Find logged in email.
     *
     * @return email
     */
    String findLoggedInEmail();

    /**
     * Auto login.
     *
     * @param email    the email
     * @param password the password
     */
    void autoLogin(String email, String password);
}