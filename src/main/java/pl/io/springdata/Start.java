//package pl.io.springdata;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Start {
//
//    private ProductRepo productRepo;
//
//    @Autowired
//    public Start(ProductRepo productRepo) {
//        this.productRepo = productRepo;
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void run() {
//        Product product = new Product("Ołówek",  1.99f, true);
//        productRepo.save(product);
//
//        product = new Product("Blok", 8.59f, true);
//        productRepo.save(product);
//
//        product = new Product("Gumka", 2.22f, false);
//        productRepo.save(product);
//    }
//
//}
