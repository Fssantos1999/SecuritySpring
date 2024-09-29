package com.felipe.auth.AuthenticatorSpring.Role;

import jakarta.persistence.*;

import com.felipe.auth.AuthenticatorSpring.Role.*;

import java.util.List;

@Entity
@Table(name = "Roles")
public class UserRole {
    private String role;
    private String username;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public UserRole() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole(String role, String username, Long id, String name) {
        this.role = role;
        this.username = username;
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
