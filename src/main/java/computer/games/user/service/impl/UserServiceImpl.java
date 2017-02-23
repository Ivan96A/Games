package computer.games.user.service.impl;

import computer.games.dto.AuthUserDTO;
import computer.games.dto.LoginUserDTO;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import computer.games.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Ivan on 28.12.2016.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public CustomUser findOneByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setOrders(null);
        userRepository.save(user);
    }

    @Override
    public AuthUserDTO authenticateUser(LoginUserDTO loginUserDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUserDTO.getUsername().trim(), loginUserDTO.getPassword());
        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (DisabledException e) {
            LOGGER.error("Failed to authenticate : " + loginUserDTO.getUsername(), e);
            return new AuthUserDTO(null, null, null, "Disable");
        } catch (BadCredentialsException e) {
            LOGGER.error("Failed to authenticate : " + loginUserDTO.getUsername(), e);
            return new AuthUserDTO(null, null, null, "BadCredentials");
        } catch (AccountExpiredException e) {
            LOGGER.error("Failed to authenticate : " + loginUserDTO.getUsername(), e);
            return new AuthUserDTO(null, null, null, "AccountExpired");
        } catch (AuthenticationException e) {
            LOGGER.error("Failed to authenticate : " + loginUserDTO.getUsername(), e);
            return new AuthUserDTO(null, null, null, "AuthenticationException");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return transformAuthenticationToAuthUserDTO(authentication);
    }

    private AuthUserDTO transformAuthenticationToAuthUserDTO(Authentication authentication) {
        if (authentication == null) {
            return new AuthUserDTO(
                    null,
                    null,
                    null,
                    "Failed to obtain authentication, please check your credentials");
        }
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        CustomUser user = userRepository.findUserByUsername(userDetails.getUsername());

        return new AuthUserDTO(user.getFirstName(), user.getUsername(), user.getRole(), "Success");
    }


}
