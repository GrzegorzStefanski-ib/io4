package pl.io.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;

    @Autowired
    public DbMockData(ProductRepo productRepo, OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {
        Product product1 = new Product("Ołówek",  1.99f, true);
        Product product2 = new Product("Blok", 8.59f, true);
        Product product3 = new Product("Gumka", 2.22f, false);

        Customer customer = new Customer("Jak Kowalski", "Wrocław");

        Set<Product> products = new HashSet<Product>() {
            {
                add(product1);
                add(product2);
                add(product3);
            }
        };
        Order order = new Order(customer, products, LocalDateTime.now(), "in progress");

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
        customerRepo.save(customer);
        orderRepo.save(order);
    }
}