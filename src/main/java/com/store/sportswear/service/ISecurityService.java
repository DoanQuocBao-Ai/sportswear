package com.store.sportswear.service;

public interface ISecurityService {
    String findLoggedInUserName();
    void autoLogin(String email,String password);
}
