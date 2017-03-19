package computer.games.order.service;

import computer.games.game.domain.Game;
import computer.games.order.domain.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Ivan on 26.01.2017.
 */
public interface OrderService {

    ResponseEntity<Order> save(String gameName, String username);

    ResponseEntity<List<Game>> getByUsername(String username);

    ResponseEntity<Double> getCost(String username);

    ResponseEntity<Void> deleteGameFromOrder(Long id);

}
