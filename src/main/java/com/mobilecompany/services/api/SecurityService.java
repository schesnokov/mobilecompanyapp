package com.mobilecompany.services.api;

public interface SecurityService {
    String findLoggedInEmail();
    void autoLogin(String email, String password);
}