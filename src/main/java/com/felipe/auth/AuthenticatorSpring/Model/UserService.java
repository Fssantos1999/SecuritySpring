package com.felipe.auth.AuthenticatorSpring.Model;

public interface UserService {
    User CreateUser(User user);
    User Login(User user);
}
