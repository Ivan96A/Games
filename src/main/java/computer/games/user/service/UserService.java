package computer.games.user.service;

import computer.games.dto.AuthUser;
import computer.games.dto.AuthUserDTO;
import computer.games.user.domain.CustomUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Ivan on 28.12.2016.
 */
public interface UserService {

    CustomUser findOneByUsername(String username);

    void save(CustomUser user);

    AuthUserDTO authenticateUser(AuthUser authUser);

}
