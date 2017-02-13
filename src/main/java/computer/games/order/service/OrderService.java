package computer.games.order.service;

import computer.games.game.domain.Game;
import computer.games.order.domain.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Ivan on 26.01.2017.
 */
public interface OrderService {

    ResponseEntity<Void> save(String gameName, String username);

    List<Game> getByUsername(String username);

    Integer getCost(String username);

    void deleteOrderGame(Long id);

}
