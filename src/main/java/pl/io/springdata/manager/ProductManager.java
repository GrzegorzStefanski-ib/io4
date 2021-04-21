package pl.io.springdata.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.io.springdata.dao.entity.Product;
import pl.io.springdata.dao.repo.ProductRepo;

import java.util.Optional;

@Service
public class ProductManager {

    private ProductRepo productRepo;

    @Autowired
    public ProductManager(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void put(Long id, Product product) {

        if(!productRepo.existsById(id)){
            throw new RuntimeException();
        }

        product.setId(id);
        productRepo.save(product);
    }

    public void patch(Long id, Product product) {

        if(!productRepo.existsById(id)){
            throw new RuntimeException();
        }

        Product oldProduct = productRepo.findById(id).get();

        String name = product.getName();
        Float price = product.getPrice();
        Boolean availability = product.isAvailable();

        if(name != null) {
            oldProduct.setName(name);
        }

        if(price != null) {
            oldProduct.setPrice(price);
        }

        if(availability != null) {
            oldProduct.setAvailable(availability);
        }

        productRepo.save(oldProduct);
    }
}
