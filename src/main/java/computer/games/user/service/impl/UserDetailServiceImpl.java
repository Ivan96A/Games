package computer.games.user.service.impl;

import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 17.01.2017.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("Init parameters method loadUserByUsername: " + email);
        CustomUser customUser = userRepository.findByUsername(email);
        User user;
        if (customUser == null) {
            throw new BadCredentialsException(email);
        }
        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(customUser.getRole()));
        user = new User(customUser.getUsername(), customUser.getPassword(), authorities);
        return user;
    }

}
