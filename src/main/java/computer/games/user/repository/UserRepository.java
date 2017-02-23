package computer.games.user.repository;

import computer.games.user.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ivan on 28.12.2016.
 */
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    @Query("select u from CustomUser u where u.username = :username")
    CustomUser findUserByUsername(@Param("username") String username);


}
