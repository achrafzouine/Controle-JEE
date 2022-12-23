package com.emsi.billingservice;

import com.emsi.billingservice.entities.Bill;
import com.emsi.billingservice.entities.ProductItem;
import com.emsi.billingservice.feign.CustomerRestClient;
import com.emsi.billingservice.feign.ProductItemRestClient;
import com.emsi.billingservice.model.Customer;
import com.emsi.billingservice.model.Product;
import com.emsi.billingservice.repositories.BillRepository;
import com.emsi.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
        
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient){

        return args -> {
            Customer customer=customerRestClient.getCustomerById(1L);

            Bill bill1=billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
            PagedModel<Product> productPagedModel=productItemRestClient.pageProducts();
            productPagedModel.forEach(p->{
               ProductItem productItem=new ProductItem();
               productItem.setProductID(p.getId());
               productItem.setPrice(p.getPrice());
               productItem.setQuantity(1+new Random().nextInt(100));
               productItem.setBill(bill1);
               productItem.setProductName(p.getName());
               productItemRepository.save(productItem);
           });
        };
    }

}
