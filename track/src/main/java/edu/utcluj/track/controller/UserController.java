package edu.utcluj.track.controller;

import edu.utcluj.track.model.User;
import edu.utcluj.track.service.UserService;
import edu.utcluj.track.utils.LocalDateTimeConverter;
import edu.utcluj.track.utils.TokenUtils;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedStoredProcedureQuery;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public Response save(@RequestBody User user) {
        log.info(user.toString());
        User newUser = null;
        try {
            newUser = userService.saveUser(user);
        }catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(("Error: " + e.getMessage())).build();
        }

        if (newUser != null) {
            return Response.status(Response.Status.OK).entity(newUser).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(("User not found ")).build();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
