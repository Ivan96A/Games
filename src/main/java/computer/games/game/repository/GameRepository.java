package computer.games.game.repository;

import computer.games.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan on 25.11.2016.
 */
public interface GameRepository extends JpaRepository<Game, Long> {

}
