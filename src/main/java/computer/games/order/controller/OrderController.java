package computer.games.order.controller;

import computer.games.order.domain.DataForSaveOrder;
import computer.games.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
    public void saveOrder(@RequestBody DataForSaveOrder dataForSaveOrder) {
         orderService.save(dataForSaveOrder.getName(), dataForSaveOrder.getUsername());
    }

}
