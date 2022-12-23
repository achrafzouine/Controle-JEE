package com.emsi.customerservice;

import com.emsi.customerservice.entities.Customer;
import com.emsi.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){

        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
          customerRepository.save(new Customer(null,"Achraf","achraf@gmail.com"));
          customerRepository.save(new Customer(null,"Adam","adam@gmail.com"));
          customerRepository.save(new Customer(null,"Ahmed","ahmed@gmail.com"));
          customerRepository.findAll().forEach(c->{
              System.out.println(c.toString());
          });
        };
    }

}
