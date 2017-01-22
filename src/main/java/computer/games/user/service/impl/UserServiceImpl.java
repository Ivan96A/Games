package computer.games.user.service.impl;

import computer.games.dto.AuthUser;
import computer.games.dto.AuthUserDTO;
import computer.games.dto.AuthUserDTOService;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import computer.games.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by Ivan on 28.12.2016.
 */

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
     AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            String username = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();

            return new UsernamePasswordAuthenticationToken(username, password);
        }
    };

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authService")
    private AuthUserDTOService authUserDTOService;

    @Override
    public CustomUser findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setOrders(null);
        userRepository.save(user);
    }

    @Override
    public AuthUserDTO authenticateUser(AuthUser authUser) {

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(authUser.getUsername().trim(), authUser.getPassword());

    Authentication authentication;

        try{
            authentication = this.authenticationManager.authenticate(authenticationToken);

        } catch (DisabledException e) {
            return new AuthUserDTO(null, null, null, "Disable");

        } catch (BadCredentialsException e) {
            return new AuthUserDTO(null, null, null, "BadCredentials");


        }catch (AccountExpiredException e) {
            return new AuthUserDTO(null, null, null, "AccountExpired");


        }catch (AuthenticationException e) {
            return new AuthUserDTO(null, null, null, "AuthenticationException");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authUserDTOService.transformAuthenticationToAuthUserDTO(authentication);
    }
}
