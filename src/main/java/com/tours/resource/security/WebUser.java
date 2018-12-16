package com.tours.resource.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by gardiary on 10/12/18.
 */
public class WebUser extends User {
    private com.tours.model.User user;

    public WebUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                   com.tours.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public com.tours.model.User getUser() {
        return user;
    }
}
