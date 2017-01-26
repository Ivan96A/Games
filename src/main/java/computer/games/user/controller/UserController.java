package computer.games.user.controller;

import computer.games.dto.AuthUserDTO;
import computer.games.dto.LoginUserDTO;
import computer.games.user.domain.CustomUser;
import computer.games.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthUserDTO login(@RequestBody LoginUserDTO loginUserDTO) {
        return userService.authenticateUser(loginUserDTO);
    }

    @RequestMapping(value = "/public/register",
            method = RequestMethod.POST)
    public void register(@RequestBody CustomUser user) {
        userService.save(user);
    }

}
