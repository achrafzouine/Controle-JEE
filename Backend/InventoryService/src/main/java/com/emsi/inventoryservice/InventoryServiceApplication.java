package com.emsi.inventoryservice;

import com.emsi.inventoryservice.entities.Product;
import com.emsi.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){

        restConfiguration.exposeIdsFor(Product.class);

        return args -> {

            productRepository.save(new Product(null,"Computer Desktop HP",900,15));
            productRepository.save(new Product(null,"Printer Epson",80,25));
            productRepository.save(new Product(null,"MacBook Pro Laptop",1800,35));
            productRepository.findAll().forEach(System.out::println);

        };

    }

}
