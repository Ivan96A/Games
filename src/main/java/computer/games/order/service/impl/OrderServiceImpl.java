package computer.games.order.service.impl;

import computer.games.game.domain.Game;
import computer.games.game.repository.GameRepository;
import computer.games.order.domain.Order;
import computer.games.order.repository.OrderRepository;
import computer.games.order.service.OrderService;
import computer.games.user.domain.CustomUser;
import computer.games.user.repository.UserRepository;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
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

    private Order order;

    private double cost;

    private Game game;

    @Override
    public ResponseEntity<Order> save(String gameName, String username) {
        if (gameName == null) {
            LOG.warn("order could not to be added because gameName is null");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (username == null) {
            LOG.warn("order could not to be added because userName is null");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        CustomUser user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOG.warn("order could not to be added because user not found");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Game game = gameRepository.findByName(gameName);
        if (game == null) {
            LOG.warn("order could not to be added because game not found");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Order order = buildOrder(user, game);
        orderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Game>> getByUsername(String username) {
        if(username == null) {
            LOG.warn("username is null");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        List<Game> games = orderRepository.findOrderGamesByUsername(username);
        if(games == null) {
            LOG.warn("games not found");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getCost(String username) {
        if (username == null) {
            LOG.warn("username is null");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        cost = 0;
        List<Game> games = orderRepository.findOrderGamesByUsername(username);
        if (games == null) {
            LOG.warn("games not found");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        cost = games.stream().mapToDouble(Game::getPrice).sum();
        return new ResponseEntity<>(new Double(cost), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteGameFromOrder(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUser user = userRepository.findUserByUsername(username);
        Order order = orderRepository.findOrderByUserId(user.getId());
        Set<Order> orders = gameRepository.getOrdersByGameId(id);
        Set<Game> gameSet = order.getGames();

        if (user == null) {
            LOG.warn("user not found");
            return ResponseEntity.badRequest().build();
        }
        if (gameSet == null) {
            LOG.warn("games not found");
            return ResponseEntity.badRequest().build();
        }

        orders = orders.stream().filter(o -> !o.getId().equals(order.getId())).collect(Collectors.toSet());

        game = gameSet.stream().filter(g -> g.getId().equals(id)).findFirst().get();

        for (Iterator<Game> iterator = gameSet.iterator(); iterator.hasNext(); ) {
            if (game.getId().equals(id)) {
                iterator.next().setOrders(orders);
                iterator.remove();
                break;
            }
        }

        order.setGames(gameSet);
        orderRepository.save(order);
        LOG.info("delete success");
        return ResponseEntity.ok().build();
    }

    private Order buildOrder(CustomUser user, Game game) {
        if (orderRepository.findOrderByUserId(user.getId()) == null) {
            order = new Order();
            order.setUser(user);
            game.addOrder(order);
            order.addGame(game);
            return order;
        } else {
            order = orderRepository.findOrderByUserId(user.getId());
            game.addOrder(order);
            order.addGame(game);
            return order;
        }
    }

}


