package computer.games.order.service.impl;

import computer.games.game.domain.Game;
import computer.games.game.repository.GameRepository;
import computer.games.order.domain.Order;
import computer.games.order.repository.OrderRepository;
import computer.games.order.service.OrderService;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 26.01.2017.
 */
@Component("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void save(String gameName, String username) {
        CustomUser user = userRepository.findByUsername(username);
        Game game = gameRepository.findByName(gameName);
        if (orderRepository.findByUserId(user.getId()) == null) {
            Order order = new Order();
            order.setUser(user);
            order.addGame(game);
            game.addOrder(order);
            orderRepository.save(order);
        }
        else {
            Order order = orderRepository.findByUserId(user.getId());
            order.addGame(game);
            game.addOrder(order);
            orderRepository.save(order);
        }
        }
    }


