package pl.io.springdata.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.io.springdata.dao.entity.Customer;
import pl.io.springdata.manager.CustomerManager;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerApi {

    private CustomerManager customers;

    @Autowired
    public CustomerApi(CustomerManager customer) {
        this.customers = customer;
    }

    @GetMapping("/customer/all")
    public Iterable<Customer> getAll() {
        return customers.findAll();
    }

    @GetMapping("/customer")
    public Optional<Customer> getById(@RequestParam Long id) {
        return customers.findById(id);
    }

    @PostMapping("/admin/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customers.save(customer);
    }

    @PutMapping("/admin/customer")
    public Customer updateCustomer(@RequestParam Long id, @RequestBody Customer customer) {
        return customers.put(id, customer);
    }

    @PatchMapping("admin/customer")
    public Customer patchCustomer(@RequestParam Long id, @RequestBody Customer customer) {
        return customers.patch(id, customer);
    }
}
