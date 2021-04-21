package pl.io.springdata.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.io.springdata.dao.entity.Customer;
import pl.io.springdata.dao.repo.CustomerRepo;


import java.util.Optional;

@Service
public class CustomerManager {

    private CustomerRepo customerRepo;

    @Autowired
    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer save(Customer product) {
        return customerRepo.save(product);
    }

    public Customer put(Long id, Customer product) {

        if(!customerRepo.existsById(id)) {
            throw new RuntimeException();
        }

        product.setId(id);
        return customerRepo.save(product);

    }

    public Customer patch(Long id, Customer product) {

        if(!customerRepo.existsById(id)) {
            throw new RuntimeException();
        }

        Customer oldCustomer = customerRepo.findById(id).get();

        String name = product.getName();
        String price = product.getAddress();

        if(name != null) {
            oldCustomer.setName(name);
        }

        if(price != null) {
            oldCustomer.setAddress(price);
        }


        return customerRepo.save(oldCustomer);
    }
}
