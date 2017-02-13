package computer.games.order.service.impl;

import computer.games.game.domain.Game;
import computer.games.game.repository.GameRepository;
import computer.games.order.domain.Order;
import computer.games.order.repository.OrderRepository;
import computer.games.order.service.OrderService;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;
import org.apache.commons.lang.ObjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 26.01.2017.
 */
@Component("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    private static Order order;

    private static int cost;

    @Override
    public ResponseEntity<Void> save(String gameName, String username) {
        if (gameName == null) {
            LOG.warn("order could not to be added because gameName is null");
            return ResponseEntity.badRequest().build();
        }
        if (username == null) {
            LOG.warn("order could not to be added because userName is null");
            return ResponseEntity.badRequest().build();
        }
        CustomUser user = userRepository.findByUsername(username);
        if (user == null) {
            LOG.warn("order could not to be added because user is null");
            return ResponseEntity.badRequest().build();
        }
        Game game = gameRepository.findByName(gameName);
        if (game == null) {
            LOG.warn("order could not to be added because game is null");
            return ResponseEntity.badRequest().build();
        }
        Order order = buildOrder(user, game);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

    @Override
    public List<Game> getByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    public Integer getCost(String username) {
        cost = 0;

        List<Game> games = orderRepository.findByUsername(username);
        for (Game game :
                games) {
            cost += game.getPrice();
        }

        return new Integer(cost);
    }

    @Override
    public void deleteOrderGame(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        Set<Game> gameSet = orderRepository.findByUserId(userRepository.findByUsername(username).getId()).
                getGames().
                stream().
                filter((g) -> g.getId() == id).
                collect(Collectors.toSet());


        orderRepository.findByUserId(userRepository.findByUsername(username).getId()).setGames(gameSet);
    }

    private Order buildOrder(CustomUser user, Game game) {
        if (orderRepository.findByUserId(user.getId()) == null) {
            order = new Order();
            order.setUser(user);
            order.addGame(game);
            game.addOrder(order);

            return order;
        } else {
            order = orderRepository.findByUserId(user.getId());
            order.addGame(game);
            game.addOrder(order);

            return order;
        }
    }

}


