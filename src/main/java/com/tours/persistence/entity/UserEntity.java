package com.tours.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gardiary on 10/12/18.
 */
@Entity
@Table(name="USERS")
public class UserEntity extends BaseEntity {
    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
