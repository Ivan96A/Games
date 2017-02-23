package computer.games.order.controller;

import computer.games.game.domain.Game;
import computer.games.order.domain.OrderDTO;
import computer.games.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ivan on 26.01.2017.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO) {
         orderService.save(orderDTO.getName(), orderDTO.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> getOrderGames(@PathVariable("username") String username) {
           return new ResponseEntity<>(orderService.getByUsername(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/cost/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getCost(@PathVariable("username") String username) {
        return new ResponseEntity<>(orderService.getCost(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/game/delete/{id}",
                    method = RequestMethod.POST)
    public ResponseEntity<Void> deleteGameFromOrder(@PathVariable("id") Long id) {
        orderService.deleteGameFromOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
