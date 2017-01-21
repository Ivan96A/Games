package computer.games.user.controller;

import computer.games.dto.AuthUser;
import computer.games.dto.AuthUserDTO;
import computer.games.user.domain.CustomUser;
import computer.games.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ivan on 28.12.2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/public/login",
           method = RequestMethod.POST)
    public AuthUserDTO login(@RequestBody AuthUser authUser) {
        return userService.authenticateUser(authUser);
    }

    @RequestMapping(value = "/public/register",
            method = RequestMethod.POST)
    public void register(@RequestBody CustomUser user) {
        userService.save(user);
    }

}
