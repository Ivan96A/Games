package computer.games.dto;

import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * Created by Ivan on 05.01.2017.
 */
public class AuthUserDTOServiceImpl implements AuthUserDTOService {

    @Autowired
    UserRepository userRepository;

    @Override
    public AuthUserDTO transformAuthenticationToAuthUserDTO(Authentication authentication) {

        if (authentication == null) {
            return new AuthUserDTO(null, null, null, null, null, "Failed to obtain authentication"); }

        Object principal = authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        CustomUser user = userRepository.findByUsername(userDetails.getUsername());


        return new AuthUserDTO(user.getFirstName(), user.getLastName(), user.getPassword(), user.getUsername(), user.getRole(),
                "Success");
    }
}