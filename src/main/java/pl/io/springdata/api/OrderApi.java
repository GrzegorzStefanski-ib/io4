package pl.io.springdata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.io.springdata.dao.entity.Order;
import pl.io.springdata.manager.OrderManager;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderApi {

    private OrderManager orders;

    @Autowired
    public OrderApi(OrderManager orders) {
        this.orders = orders;
    }

    @GetMapping("/order/all")
    public Iterable<Order> getAll() {
        return orders.findAll();
    }

    @GetMapping("/order")
    public Optional<Order> getById(@RequestParam Long id) {
        return orders.findById(id);
    }

    @PostMapping("/order")
    public Order addOrder(@RequestBody Order order) {
        return orders.save(order);
    }

    @PutMapping("/admin/order")
    public Order updateOrder(@RequestParam Long id, @RequestBody Order order) {
        return orders.put(id, order);
    }

    @PatchMapping("admin/order")
    public Order patchOrder(@RequestParam Long id, @RequestBody Order order) {
        return orders.patch(id, order);
    }
}
