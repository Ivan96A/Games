package computer.games.order.repository;

import computer.games.game.domain.Game;
import computer.games.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ivan on 26.01.2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id = :id")
    Order findByUserId(@Param("id") Long id);

    @Query("select o.games from Order o where o.user.username = :username")
    List<Game> findByUsername(@Param("username") String username);

}
