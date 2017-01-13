package computer.games.user.service.impl;

import computer.games.dto.AuthUserDTO;
import computer.games.dto.AuthUserDTOService;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import computer.games.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Ivan on 28.12.2016.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

   // @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   // @Autowired
    AuthUserDTOService authUserDTOService;

    @Override
    public CustomUser findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(CustomUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setOrders(null);
        userRepository.save(user);
    }

    @Override
    public AuthUserDTO authenticateUser(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(username.trim(), password);

        Authentication authentication = null;


        try{
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (DisabledException e) {

        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authUserDTOService.transformAuthenticationToAuthUserDTO(authentication);
    }
}
