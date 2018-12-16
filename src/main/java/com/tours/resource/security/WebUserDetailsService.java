package com.tours.resource.security;

import com.tours.model.User;
import com.tours.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gardiary on 10/12/18.
 */
@Service("userDetailsService")
public class WebUserDetailsService implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        LOGGER.info("User : {}", user);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password for '" + username + "'");
        }

        if(user.getRole() == null) {
            LOGGER.error("Username '" + username + "' have no roles");
            throw new BadCredentialsException("Bad credentials for username '" + username + "'");
        }

        Set<GrantedAuthority> authSet = new HashSet<>();
        authSet.add(new SimpleGrantedAuthority(user.getRole()));

        List<GrantedAuthority> authorities = new ArrayList<>(authSet);

        return new WebUser(user.getUsername(), user.getPassword(), authorities, user);
    }
}
