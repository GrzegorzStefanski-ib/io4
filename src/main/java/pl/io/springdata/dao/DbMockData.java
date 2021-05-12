package pl.io.springdata.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.io.springdata.dao.entity.Customer;
import pl.io.springdata.dao.entity.Order;
import pl.io.springdata.dao.entity.Product;
import pl.io.springdata.dao.repo.CustomerRepo;
import pl.io.springdata.dao.repo.OrderRepo;
import pl.io.springdata.dao.repo.ProductRepo;
import pl.io.springdata.dao.repo.UserRepo;
import pl.io.springdata.utils.User;
import pl.io.springdata.utils.UserDtoBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private UserRepo userRepo;

    private UserDtoBuilder userDtoBuilder;

    @Autowired
    public DbMockData(
            ProductRepo productRepo,
            OrderRepo orderRepo,
            CustomerRepo customerRepo,
            UserRepo userRepo,
            UserDtoBuilder userDtoBuilder
    ) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.userDtoBuilder = userDtoBuilder;
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

        User user1 = new User("User1", "pass1", "ROLE_CUSTOMER");
        User user2 = new User("User2", "pass2", "ROLE_ADMIN");

        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
        customerRepo.save(customer);
        orderRepo.save(order);
        userRepo.save(userDtoBuilder.build(user1));
        userRepo.save(userDtoBuilder.build(user2));

    }
}