package computer.games.game.repository;

import computer.games.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ivan on 25.11.2016.
 */
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g where g.name = :name")
    Game findByName(@Param("name") String name);

}
