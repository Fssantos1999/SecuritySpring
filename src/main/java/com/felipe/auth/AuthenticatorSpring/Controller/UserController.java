package com.felipe.auth.AuthenticatorSpring.Controller;

import com.felipe.auth.AuthenticatorSpring.Model.User;
import com.felipe.auth.AuthenticatorSpring.Model.UserService;
import com.felipe.auth.AuthenticatorSpring.Model.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;
@PostMapping()
public User createUser(@RequestBody User user) {
    return userService.CreateUser(user);
}



}
