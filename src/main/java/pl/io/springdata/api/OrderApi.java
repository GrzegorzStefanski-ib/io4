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

    @PostMapping("/admin/order")
    public Order addOrder(@RequestBody Order order) {
        return orders.save(order);
    }

    @PutMapping("/admin/order")
    public void updateOrder(@RequestParam Long id, @RequestBody Order order) {
        orders.put(id, order);
    }

    @PatchMapping("admin/order")
    public void patchOrder(@RequestParam Long id, @RequestBody Order order) {
        orders.patch(id, order);
    }
}
