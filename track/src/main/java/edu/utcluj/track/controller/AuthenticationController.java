package edu.utcluj.track.controller;

import edu.utcluj.track.model.User;
import edu.utcluj.track.model.UserLogin;
import edu.utcluj.track.service.UserService;
import edu.utcluj.track.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping(value = "/")
public class AuthenticationController {

    public static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user", response = Response.class),
            @ApiResponse(code = 404, message = "User with given id does not exist"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response loginUser(@RequestBody UserLogin userLogin) {
        log.trace(userLogin.getUsername() + "--" + userLogin.getPassword());
        User user = null;
        String username = null;
        String password = null;
        try {
            username = userLogin.getUsername();
            password = userLogin.getPassword();
            user = userService.getUserByUsername(username);

        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(("Error: " + e.getMessage())).build();
        }

        if ((user != null) && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return Response.status(Response.Status.OK).entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(("User not found ")).build();
    }
}
