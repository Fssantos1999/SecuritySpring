package com.felipe.auth.AuthenticatorSpring.Model;


import com.felipe.auth.AuthenticatorSpring.Role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
   @Autowired
private UserRepository userRepository;
public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();

}

    @Override
    public User CreateUser(User user) {
         User existingUser = userRepository.findByUsername(user.getUsername());
         if (existingUser != null) {
             throw  new RuntimeException("Username already exists");
         }
         else {


             user.setPassword(getPasswordEncoder().encode(user.getPassword()));
             User newUser = userRepository.save(user);
              return newUser;
         }
    }

    @Override
    public User Login(User user) {
        return null;
    }
}
