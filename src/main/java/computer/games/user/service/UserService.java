package computer.games.user.service;

import computer.games.dto.LoginUserDTO;
import computer.games.dto.AuthUserDTO;
import computer.games.user.domain.CustomUser;

/**
 * Created by Ivan on 28.12.2016.
 */
public interface UserService {

    CustomUser findOneByUsername(String username);

    void save(CustomUser user);

    AuthUserDTO authenticateUser(LoginUserDTO loginUserDTO);

}
