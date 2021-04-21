package pl.io.springdata.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.io.springdata.dao.entity.Product;
import pl.io.springdata.manager.ProductManager;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductApi {

    private ProductManager products;

    @Autowired
    public ProductApi(ProductManager products) {
        this.products = products;
    }

    @GetMapping("/product/all")
    public Iterable<Product> getAll() {
        return products.findAll();
    }

    @GetMapping("/product")
    public Optional<Product> getById(@RequestParam Long id) {
        return products.findById(id);
    }

    @PostMapping("/admin/product")
    public Product addProduct(@RequestBody Product product) {
        return products.save(product);
    }

    @PutMapping("/admin/product")
    public Product updateProduct(@RequestParam Long id, @RequestBody Product product) {
        return products.put(id, product);
    }

    @PatchMapping("admin/product")
    public Product patchProduct(@RequestParam Long id, @RequestBody Product product) {
        return products.patch(id, product);
    }
}
