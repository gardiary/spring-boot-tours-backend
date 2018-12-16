package com.tours.resource;

import com.tours.model.GenericMessage;
import com.tours.model.User;
import com.tours.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gardiary on 10/12/18.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/save", method = {RequestMethod.GET})
    public ResponseEntity<GenericMessage> save(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("role") String role) {

        User user = new User(username, passwordEncoder.encode(password), role);

        userService.save(user);

        return new ResponseEntity<GenericMessage>(new GenericMessage("User successfully created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public ResponseEntity<User> getUser(@RequestParam("username") String username) {
        User user = userService.getByUsername(username);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
