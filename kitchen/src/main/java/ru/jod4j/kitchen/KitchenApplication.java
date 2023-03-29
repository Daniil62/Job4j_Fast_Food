package ru.jod4j.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitchenApplication.class, args);
        System.out.println("Go to http://localhost:8084/kitchen");
    }
}