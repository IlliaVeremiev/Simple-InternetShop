package veil.internetshop.simple.cronjobs;

import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.repositories.ProductRepository;

/**
 * Updates products status according to warehouse stock count
 */
public class ProductWarehouseStatusUpdateCronJob implements Runnable{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(){
        productRepository.updateProductWarehouseStatus();
    }
}
