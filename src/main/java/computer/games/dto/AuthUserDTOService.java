package computer.games.dto;

import org.springframework.security.core.Authentication;

/**
 * Created by Ivan on 05.01.2017.
 */
public interface AuthUserDTOService {

    AuthUserDTO transformAuthenticationToAuthUserDTO(Authentication authentication);
}
