package pl.io.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private ProductRepo productRepo;

    @Autowired
    public Start(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        Product product = new Product("Ołówek", (float) 1.99, true);
        productRepo.save(product);

        product = new Product("Blok", (float) 8.59, true);
        productRepo.save(product);

        product = new Product("Gumka", (float) 2.22, false);
        productRepo.save(product);
    }
}
