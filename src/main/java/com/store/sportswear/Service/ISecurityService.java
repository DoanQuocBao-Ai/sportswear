package com.store.sportswear.Service;

public interface ISecurityService {
    String findLoggedInUserName();
    void autoLogin(String email,String password);
}
