package pl.io.springdata.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.io.springdata.dao.entity.Customer;
import pl.io.springdata.dao.entity.Order;
import pl.io.springdata.dao.entity.Product;
import pl.io.springdata.dao.repo.OrderRepo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderManager {

    private OrderRepo orderRepo;

    @Autowired
    public OrderManager(OrderRepo productRepo) {
        this.orderRepo = productRepo;
    }

    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    public Order put(Long id, Order order) {

        if(!orderRepo.existsById(id)){
            throw new RuntimeException();
        }

        order.setId(id);
        return orderRepo.save(order);
    }

    public Order patch(Long id, Order order) {

        if(!orderRepo.existsById(id)){
            throw new RuntimeException();
        }

        Order oldOrder = orderRepo.findById(id).get();

        Customer customer = order.getCustomer();
        Set<Product> products = order.getProducts();
        LocalDateTime placeDate = order.getPlaceDate();
        String status = order.getStatus();



        if(customer != null) {
            oldOrder.setCustomer(customer);
        }

        if(products != null) {
            oldOrder.setProducts(products);
        }

        if(placeDate != null) {
            oldOrder.setPlaceDate(placeDate);
        }

        if(status != null) {
            oldOrder.setStatus(status);
        }

        return orderRepo.save(oldOrder);
    }
}
