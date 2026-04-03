package pe.edu.idat.loan_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            System.out.println("=====================================");
            System.out.println("🚀 LOAN-SERVICE LEVANTADO");
            System.out.println("🌐 URL: http://localhost:8083");
            System.out.println("=====================================");
        };
    }
}