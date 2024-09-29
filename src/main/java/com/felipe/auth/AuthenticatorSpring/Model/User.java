package com.felipe.auth.AuthenticatorSpring.Model;

import com.felipe.auth.AuthenticatorSpring.Role.UserRole;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User  {
private String username;
private String password;
private boolean enabled;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToMany
    private List<UserRole> userRoles;

    public User(String username, String password, boolean enabled, List<UserRole> userRoles, Long id) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRoles = userRoles;
        this.id = id;
    }


    public User() {}





    public User(List<UserRole> userRoles) {
        this.userRoles = userRoles;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}