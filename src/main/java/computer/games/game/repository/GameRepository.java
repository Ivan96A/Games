package computer.games.game.repository;

import computer.games.game.domain.Game;
import computer.games.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by Ivan on 25.11.2016.
 */
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g where g.name = :name")
    Game findByName(@Param("name") String name);

    @Query("select g.orders from Game g where g.id = :id")
    Set<Order> getOrdersByGameId(@Param("id") Long id);

}
