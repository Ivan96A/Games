package computer.games.user.service;

import computer.games.dto.LoginUserDTO;
import computer.games.dto.AuthUserDTO;
import computer.games.user.domain.CustomUser;
import org.springframework.http.ResponseEntity;

/**
 * Created by Ivan on 28.12.2016.
 */
public interface UserService {

    ResponseEntity<CustomUser> findOneByUsername(String username);

    ResponseEntity<Void> save(CustomUser user);

    AuthUserDTO authenticateUser(LoginUserDTO loginUserDTO);

}
