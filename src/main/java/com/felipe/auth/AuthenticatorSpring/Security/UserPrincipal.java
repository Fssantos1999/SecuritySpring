package com.felipe.auth.AuthenticatorSpring.Security;

import com.felipe.auth.AuthenticatorSpring.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserPrincipal(User user) {
        this.username = username;
        this.password = password;
        this.authorities = user.getUserRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE".concat(role.getName()));
        }).collect(Collectors.toList());

    }

    public static UserPrincipal fromUser(User user) {
        return new UserPrincipal(user);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
