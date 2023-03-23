package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository
                                              ,RepositoryRestConfiguration repositoryRestConfiguration
    ){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Smatphone").price(1500).quantity(800).build(),
                            Product.builder().name("Laptop").price(800).quantity(25).build(),
                            Product.builder().name("Monitor").price(1200).quantity(100).build(),
                            Product.builder().name("Printer").price(2500).quantity(50).build()
                    )
            );

            productRepository.findAll().forEach(c ->{
                System.out.println(c);
            });
        };
    }
}
